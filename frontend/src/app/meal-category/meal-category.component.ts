import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-meal-category',
  templateUrl: './meal-category.component.html',
  styleUrls: ['./meal-category.component.css']
})
export class MealCategoryComponent implements OnInit {

  //showMoney:boolean

  constructor(private router:Router) { 
    //this.showMoney = history.state.data.showMoney;
    //console.log(this.showMoney)
  }

  ngOnInit(): void {
  }



  coldAppetizer(){
    this.router.navigate(['/ColdAppetizer'], {state:{data:{showPrice:true}}});
  }

  hotAppetizer(){
    this.router.navigate(['/HotAppetizer'], {state:{data:{showPrice:true}}});
  }

}

