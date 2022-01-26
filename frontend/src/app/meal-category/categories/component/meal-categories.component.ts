import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';


@Component({
  selector: 'app-meal-categories',
  templateUrl: './meal-categories.component.html',
  styleUrls: ['./meal-categories.component.css']
})
export class MealCategoriesComponent implements OnInit {

  constructor(private router:Router) { }

  ngOnInit(): void {
  }



  //salji

  getColdAppetizers(){
    this.router.navigate(['/MealsOfCategory'], {state:{data:{category:1}}});
  }

  getHotAppetizers(){
    this.router.navigate(['/MealsOfCategory'], {state:{data:{category:2}}});
  }


  getMainMeals(){
    this.router.navigate(['/MealsOfCategory'], {state:{data:{category:3}}});
  }


  getDesserts(){
    this.router.navigate(['/MealsOfCategory'], {state:{data:{category:4}}});
  }


  getSalats(){
    this.router.navigate(['/MealsOfCategory'], {state:{data:{category:5}}});
  }


  getAppendices(){
    this.router.navigate(['/MealsOfCategory'], {state:{data:{category:6}}});
  }


  returnToProfile(){
    this.router.navigate(['/ChefProfile']);
  }
}

