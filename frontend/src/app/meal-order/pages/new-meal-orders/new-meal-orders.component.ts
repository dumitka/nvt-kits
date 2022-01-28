import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MealOrderService } from '../../service/meal-order.service';
import { MatSnackBar, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
@Component({
  selector: 'app-new-meal-orders',
  templateUrl: './new-meal-orders.component.html',
  styleUrls: ['./new-meal-orders.component.css']
})
export class NewMealOrdersComponent implements OnInit {

  displayedColumns: string[] = ['position', 'name', 'numOfPortion', 'button'];
  listOfOrders: any[];

  isChef:boolean;
  userId:number;

  RESPONSE_OK : number = 0;
  RESPONSE_ERROR : number = -1;
  verticalPosition: MatSnackBarVerticalPosition = "top";


  constructor(private router:Router, private service:MealOrderService, private snackBar: MatSnackBar) {
    this.isChef = history.state.data.isChef;
    this.userId = history.state.data.userId;

    this.service.notAcceptedOrderedMeals().subscribe((data:any) => {
      this.listOfOrders = data;
      console.log(this.listOfOrders);
    })


  }

  ngOnInit(): void {
  }


  take(order:any){
    this.service.acceptOrderedMeal(order.id).subscribe(
      response => {
        this.openSnackBar("Poručeno jelo je uspešno prihvaćeno!", this.RESPONSE_OK);
        this.service.notAcceptedOrderedMeals().subscribe((data:any) => {
          this.listOfOrders = data;
        })
    
      },
      error => {
      this.openSnackBar("Došlo je do nekakve greške, molim Vas pokušajte ponovo! :)", this.RESPONSE_ERROR);
      }
    )
  }


  takenMeals(){
    if(this.isChef){
      this.router.navigate(['/TakenMealOrders'], {state:{data:{"isChef": this.isChef, "userId":this.userId}}});
    }else{
      this.router.navigate(['/TakenMealOrdersCook'], {state:{data:{"isChef": this.isChef, "userId":this.userId}}});
    }
   
  }


  back(){
    if(this.isChef){
      this.router.navigate(['/ChefProfile']);
    }else{
      this.router.navigate(['/CookProfile']);
    }
    
  }


  openSnackBar(msg: string, responseCode: number) {
    this.snackBar.open(msg, "x", {
      duration: responseCode === this.RESPONSE_OK ? 3000 : 20000,
      verticalPosition: this.verticalPosition,
      panelClass: responseCode === this.RESPONSE_OK ? "back-green" : "back-red"
    });
  }
}
