import { Component, OnInit } from '@angular/core';
import { MatSnackBar, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { DrinkDTO } from 'src/app/models/drinkDTO';
import { DeskOrderService } from 'src/app/orders/services/desk-order.service';

interface OrderedItem {
  name: string;
  amount: number;
  isDrink: boolean;
  drinkId: number;
  mealId: number;
}

@Component({
  selector: 'app-view-order',
  templateUrl: './view-order.component.html',
  styleUrls: ['./view-order.component.css']
})
export class ViewOrderComponent implements OnInit {
  orderId: number;
  deskId: number = history.state.data.deskId;
  selectedItems: DrinkDTO[] = history.state.data.selectedItems;
  displayedColumns: string[] = ['position', 'name', 'amount', 'remove'];
  dataSource = new MatTableDataSource<OrderedItem>();
  responseCode: number = -1;
  RESPONSE_OK: number = 0;
  RESPONSE_ERROR: number = -1;
  verticalPosition: MatSnackBarVerticalPosition = "top";

  constructor(private service: DeskOrderService, private router: Router, private snackBar: MatSnackBar) {
    //TODO: dodati amount sabiranje
    this.setData();
  }

  ngOnInit(): void {

  }

  setData(): void {
    let data: OrderedItem[] = [];
    this.selectedItems.forEach(element => {
      data.push({
        'amount': 1,
        'isDrink': element.isDrink,
        'name': element.name,
        'drinkId': element.id,
        'mealId': element.id
      });
    });
    this.dataSource.data = data;
  }

  remove(id: number): void {
    this.selectedItems = this.selectedItems.filter(function (obj) {
      return obj.id !== id;
    });
    this.setData();
  }

  createOrder(): void {
    this.service.createOrder(this.deskId).subscribe(response => {
      this.orderId = parseInt(response);

      this.dataSource.data.forEach(element => {
        if (element.isDrink) {
          this.service.createOrderedDrink(this.orderId, element).subscribe(response => {
            if (response.status === 200) {
              this.responseCode = this.RESPONSE_OK;
            } else {
              this.responseCode = this.RESPONSE_ERROR;
            }
          });
        } else {
          this.service.createOrderedMeal(this.orderId, element).subscribe(response => {
            if (response.status === 200) {
              this.responseCode = this.RESPONSE_OK;
            } else {
              this.responseCode = this.RESPONSE_ERROR;
            }
          });
        }
      });
    });

    this.openSnackBar("Porudžbina uspešno kreirana!", this.responseCode);
    this.router.navigate(['/WaiterProfile']);
  }

  back(): void {
    this.router.navigate(['/CreateOrder'], { state: { data: { 'deskId': this.deskId, 'selectedItems': this.selectedItems } } });
  }

  openSnackBar(message: string, responseCode: number): void {
    this.snackBar.open(message, 'x', {
      duration: responseCode === this.RESPONSE_OK ? 3000 : 20000,
      verticalPosition: this.verticalPosition
    });
  }
}
