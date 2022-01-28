import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-new-meal-orders',
  templateUrl: './new-meal-orders.component.html',
  styleUrls: ['./new-meal-orders.component.css']
})
export class NewMealOrdersComponent implements OnInit {

  displayedColumns: string[] = ['position', 'name', 'numOfPortion', 'button'];
  listOfOrders: any[];


  constructor(private router:Router) { }

  ngOnInit(): void {
  }


  take(order:any){

  }


  takenMeals(){
    this.router.navigate(['/TakenMealOrders']);
  }


  back(){
  
  }
}
