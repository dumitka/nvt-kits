import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-meal-profile',
  templateUrl: './meal-profile.component.html',
  styleUrls: ['./meal-profile.component.css']
})
export class MealProfileComponent implements OnInit {
  meal:any;

  mealHasItsOwnPicture : boolean = false;
  mealDoesNotHaveItsOwnPicture:boolean = false;

  prepDifficulty:string;


  constructor() { 
    this.meal = history.state.data.meal;

    if(this.meal.image != "nema"){
      this.mealHasItsOwnPicture = true;
    }else{
      this.mealDoesNotHaveItsOwnPicture = true;
    }

    if(this.meal.mealDifficulty == "EASY"){
      this.prepDifficulty = "LAKO";
    }else if(this.meal.mealDifficulty == "MEDIUM"){
      this.prepDifficulty = "SREDNJE";
    }else{
      this.prepDifficulty = "TESKO";
    }


    console.log(this.meal);
  }

  ngOnInit(): void {
  }


  change(){

  }


  delete(){
    
  }
}
