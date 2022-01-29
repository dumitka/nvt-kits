import { Component, OnInit } from '@angular/core';
import { MatSnackBar, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { DrinkOrderService } from '../../service/drink-order.service';

@Component({
  selector: 'app-taken-drink-orders',
  templateUrl: './taken-drink-orders.component.html',
  styleUrls: ['./taken-drink-orders.component.css']
})
export class TakenDrinkOrdersComponent implements OnInit {

  displayedColumns: string[] = ['position', 'name', 'numOfPortion', 'button'];
  listOfOrders: any[];

  userId:number;

  RESPONSE_OK : number = 0;
  RESPONSE_ERROR : number = -1;
  verticalPosition: MatSnackBarVerticalPosition = "top";


  constructor(private router:Router, private service:DrinkOrderService, private snackBar: MatSnackBar) {
    this.userId = history.state.data.userId;

    this.service.acceptedOrderedDrinks(this.userId).subscribe((data:any) => {
      this.listOfOrders = data;
      console.log(this.listOfOrders);
    })


  }

  ngOnInit(): void {
  }


  end(order:any){
    this.service.finishOrderedDrink(order.id).subscribe(
      response => {
        this.openSnackBar("Poručeno piće je uspešno završeno!", this.RESPONSE_OK);
        this.service.acceptedOrderedDrinks(this.userId).subscribe((data:any) => {
          this.listOfOrders = data;
        })
    
      },
      error => {
      this.openSnackBar("Došlo je do nekakve greške, molim Vas pokušajte ponovo! :)", this.RESPONSE_ERROR);
      }
    )
  }


  back(){
      this.router.navigate(['/NewDrinkOrders'], {state:{data:{"userId":this.userId}}});

  }


  openSnackBar(msg: string, responseCode: number) {
    this.snackBar.open(msg, "x", {
      duration: responseCode === this.RESPONSE_OK ? 3000 : 20000,
      verticalPosition: this.verticalPosition,
      panelClass: responseCode === this.RESPONSE_OK ? "back-green" : "back-red"
    });
  }

}
