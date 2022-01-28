import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-taken-meal-orders',
  templateUrl: './taken-meal-orders.component.html',
  styleUrls: ['./taken-meal-orders.component.css']
})
export class TakenMealOrdersComponent implements OnInit {

  displayedColumns: string[] = ['position', 'name', 'numOfPortion', 'button'];
  listOfOrders: any[];


  constructor() { }

  ngOnInit(): void {
  }


  end(order:any){

  }




  back(){

  }

}
