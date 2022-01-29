import { Component, OnInit } from '@angular/core';
import { DeskOrderService } from '../../services/desk-order.service';
import { Router } from '@angular/router';
import { Desk } from 'src/app/models/desk';
import { Order } from 'src/app/models/order';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import { DeleteOrderDialogComponent } from '../../components/delete-order-dialog/delete-order-dialog.component';
import { ChargeOrderDialogComponent } from '../../components/charge-order-dialog/charge-order-dialog.component';

@Component({
  selector: 'app-desk-order',
  templateUrl: './desk-order.component.html',
  styleUrls: ['./desk-order.component.css']
})
export class DeskOrderComponent implements OnInit {
  deskId: number;
  deskNumber: number;
  desk: Desk = new Desk();
  order: Order = new Order();
  ordered: boolean = false;
  deliveredDrinks: boolean = false;
  deliveredMeals: boolean = false;
  delivered: boolean = false;

  RESPONSE_OK: number = 0;
  RESPONSE_ERROR: number = -1;
  verticalPosition: MatSnackBarVerticalPosition = "top";

  constructor(private service: DeskOrderService, private router: Router, private dialog: MatDialog, private snackBar: MatSnackBar) {
    this.deskId = history.state.data.id;
    this.deskNumber = history.state.data.number;
    
    this.service.getDesk(this.deskId).subscribe((response: Desk | null) => {
      this.desk = response;
      this.desk.deskNumber = this.deskNumber;

      let deskStatus: string = this.desk.deskStatus.toString();
      if (deskStatus === 'NOT_ORDERED') {
        this.ordered = false;
      } else {
        this.ordered = true;
      }

      switch (deskStatus) {
        case 'DELIVERED':
          this.deliveredDrinks = true;
          this.deliveredMeals = true;
          this.delivered = true;
          break;
        case 'DELIVERED_DRINKS':
          this.deliveredDrinks = true;
          break;
        case 'DELIVERED_MEALS':
          this.deliveredMeals = true;
          break;
        default:
          this.deliveredDrinks = false;
          this.deliveredMeals = false;
          this.delivered = false;
          break;
      }
    });

    this.service.getOrder(this.deskId).subscribe((response: Order | null) => {
      this.order = response;
    });
  }

  ngOnInit(): void {

  }

  openSnackBar(message: string, responseCode: number) {
    this.snackBar.open(message, 'x', {
      duration: responseCode === this.RESPONSE_OK ? 3000 : 20000,
      verticalPosition: this.verticalPosition
    });
  }

  createOrder(): void {
    this.router.navigate(['/CreateOrder'])
  }

  updateOrder(): void {

  }

  deleteOrder(): void {
    let deleteOrderDialog = this.dialog.open(DeleteOrderDialogComponent, {
      height: '30%',
      width: '45%',
    });

    deleteOrderDialog.afterClosed().subscribe(returnValue => {
      if (returnValue === 'true') {
        this.service.deleteOrder(this.order.id).subscribe(
          response => {
            this.openSnackBar(response, this.RESPONSE_OK);
          },
          error => {
            this.openSnackBar(error.error, this.RESPONSE_ERROR);
          }
        );
        this.router.navigate(['/WaiterProfile'])
      }
    });
  }

  serveDrinks(): void {
    this.service.serveDrinks(this.desk.id).subscribe(
      response => {
        this.openSnackBar(response, this.RESPONSE_OK);
      },
      error => {
        this.openSnackBar(error.error, this.RESPONSE_ERROR);
      }
    );
    this.router.navigate(['/WaiterProfile'])
  }

  serveMeals(): void {
    this.service.serveMeals(this.desk.id).subscribe(
      response => {
        this.openSnackBar(response, this.RESPONSE_OK);
      },
      error => {
        this.openSnackBar(error.error, this.RESPONSE_ERROR);
      }
    );
    this.router.navigate(['/WaiterProfile']) 
  }

  chargeOrder(): void {
    this.service.chargeOrder(this.order.id).subscribe(
      response => {
        let chargeOrderDialog = this.dialog.open(ChargeOrderDialogComponent, {
          height: '30%',
          width: '45%',
          data: { "chargeMessage": response },
        });

        chargeOrderDialog.afterClosed().subscribe(() => {
          this.router.navigate(['/WaiterProfile']) 
          this.openSnackBar("Porudžbina uspešno završena!", this.RESPONSE_OK);
        });
      },
      error => {
        this.router.navigate(['/WaiterProfile']) 
        this.openSnackBar(error.error, this.RESPONSE_ERROR);
      }
    )
  }

  back(): void {
    this.router.navigate(['/WaiterProfile']) 
  }
}
