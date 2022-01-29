import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MatSnackBar, MatSnackBarVerticalPosition, _SnackBarContainer } from '@angular/material/snack-bar';
import { DrinkService } from 'src/app/drinks/services/drink-service/drink.service';
import { DrinkOrderService } from '../../service/drink-order.service';

@Component({
  selector: 'app-new-drink-orders',
  templateUrl: './new-drink-orders.component.html',
  styleUrls: ['./new-drink-orders.component.css']
})
export class NewDrinkOrdersComponent implements OnInit {

  displayedColumns: string[] = ['position', 'name', 'numOfPortion', 'button'];
  listOfOrders: any[];

  userId: number;

  RESPONSE_OK : number = 0;
  RESPONSE_ERROR : number = -1;
  verticalPosition: MatSnackBarVerticalPosition = "top";

  constructor(private router:Router, private service: DrinkOrderService, private snackBar: MatSnackBar) {
      this.userId = history.state.data.userId; //moze i preko local storage-a
      
      this.service.notAcceptedOrderedDrinks().subscribe((data:any) => {
        this.listOfOrders = data;
        console.log(this.listOfOrders)
      })
      
  }


  take(order:any){
    this.service.acceptOrderedDrink(order.id).subscribe(
      response => {
        this.openSnackBar("Poručeno piće je uspešno prihvaćeno!", this.RESPONSE_OK);
        this.service.notAcceptedOrderedDrinks().subscribe((data:any) => {
          this.listOfOrders = data;
        })
    
      },
      error => {
      this.openSnackBar("Došlo je do nekakve greške, molim Vas pokušajte ponovo! :)", this.RESPONSE_ERROR);
      }
    )
  }



  ngOnInit(): void {
  }

  goToTakenDrinks() {
    this.router.navigate(['/TakenDrinkOrders'],  {state:{data:{"userId":this.userId}}});
  }

  goBack() {
    this.router.navigate(['/BartenderProfile']);
  }


  openSnackBar(msg: string, responseCode: number) {
    this.snackBar.open(msg, "x", {
      duration: responseCode === this.RESPONSE_OK ? 3000 : 20000,
      verticalPosition: this.verticalPosition,
      panelClass: responseCode === this.RESPONSE_OK ? "back-green" : "back-red"
    });
  }
}
