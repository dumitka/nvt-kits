import { Component, OnInit } from '@angular/core';
import { DeskOrderService } from '../../services/desk-order.service';
import { Router } from '@angular/router';
import { Desk } from 'src/app/models/desk';
import { Order } from 'src/app/models/order';
import { MatDialog } from '@angular/material/dialog';
import { DeleteOrderDialogComponent } from '../../components/delete-order-dialog/delete-order-dialog.component';
import { MatSnackBar, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';

@Component({
  selector: 'app-desk-order',
  templateUrl: './desk-order.component.html',
  styleUrls: ['./desk-order.component.css']
})
export class DeskOrderComponent implements OnInit {
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
    this.service.getDesk().subscribe((data: Desk | null) => {
      this.desk = data as Desk;

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

    this.service.getOrder().subscribe((data: any) => {
      this.order = data;
    });
  }

  ngOnInit(): void {

  }

  openSnackBar(msg: string, responseCode: number) {
    this.snackBar.open(msg, "x", {
      duration: responseCode === this.RESPONSE_OK ? 3000 : 20000,
      verticalPosition: this.verticalPosition,
      panelClass: responseCode === this.RESPONSE_OK ? "back-green" : "back-red"
    });
  }

  delete(): void {
    let dialogReturnValue = this.dialog.open(DeleteOrderDialogComponent, {
      height: '30%',
      width: '45%',
    });

    dialogReturnValue.afterClosed().subscribe(returnValue => {
      if (returnValue === "true") {
        this.service.deleteOrder(this.order.id).subscribe(
          response => {
            this.openSnackBar(response, this.RESPONSE_OK);
          },
          error => {
            this.openSnackBar(error.error, this.RESPONSE_ERROR);
          }
        )
        this.router.navigate(['/login']) // TODO: prozor sa stolovima
      }
    });
  }

  back(): void {
    this.router.navigate(['/login']) // TODO: prozor sa stolovima
  }
}
