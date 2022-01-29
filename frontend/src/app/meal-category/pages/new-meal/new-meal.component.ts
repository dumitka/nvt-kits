import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MealServiceService } from '../../service/meal-service.service';
import { MatSnackBar, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import { Router } from '@angular/router';

@Component({
  selector: 'app-new-meal',
  templateUrl: './new-meal.component.html',
  styleUrls: ['./new-meal.component.css']
})
export class NewMealComponent implements OnInit {

  public newMealForm: FormGroup;

  RESPONSE_OK : number = 0;
  RESPONSE_ERROR : number = -1;

  type;

  verticalPosition: MatSnackBarVerticalPosition = "top";



  constructor(private formBuilder: FormBuilder, 
                      private service:MealServiceService,
                      private snackBar: MatSnackBar,
                      private router:Router) {


    this.type = history.state.data.type;

    this.newMealForm = this.formBuilder.group({
      name:['', Validators.required],
      description:['', Validators.required],
      type:['', Validators.required],
      timePreparation:['', [Validators.required, Validators.min(1)]], 
      mealDifficulty:['', Validators.required],
      amountNumber: ['', [Validators.required, Validators.min(1)]],
      amountUnit: ['', Validators.required],
      image: ['nema']
    });

  }

  ngOnInit(): void {
  }



  public requiredError = (controlName: string, errorName: string) =>{
    return this.newMealForm.controls[controlName].hasError(errorName);
  }


  public mindError = (controlName: string, errorName: string) =>{
    return this.newMealForm.controls[controlName].hasError(errorName);
  }


  public addNewMeal(){
    console.log(this.newMealForm.value);
    
    this.service.addMeal(this.newMealForm.value).subscribe(
      response => {
       this.openSnackBar("Uspešno ste dodali jelo", this.RESPONSE_OK);
       if(this.newMealForm.value.type == "COLD_APPETIZER")this.router.navigate(['/MealsOfCategory'], {state:{data:{category:1}}});
       else if(this.newMealForm.value.type == "HOT_APPETIZER") this.router.navigate(['/MealsOfCategory'], {state:{data:{category:2}}});
       else if(this.newMealForm.value.type == "MAIN_COURSE") this.router.navigate(['/MealsOfCategory'], {state:{data:{category:3}}});
       else if(this.newMealForm.value.type == "DESERT") this.router.navigate(['/MealsOfCategory'], {state:{data:{category:4}}});
       else if(this.newMealForm.value.type == "SALAD") this.router.navigate(['/MealsOfCategory'], {state:{data:{category:5}}});
       else this.router.navigate(['/MealsOfCategory'], {state:{data:{category:6}}});
      },
      error => {
        this.openSnackBar("Jelo sa datim imenom i opisom već postoji. Dodajte novo jelo. :)", this.RESPONSE_ERROR);
      }
    )
  }



  back(){ 
    if(this.type == 1){
      this.router.navigate(['/MealsOfCategory'], {state:{data:{category:1}}});
    }else if(this.type == 2){
      this.router.navigate(['/MealsOfCategory'], {state:{data:{category:2}}});
    }else if(this.type == 3){
      this.router.navigate(['/MealsOfCategory'], {state:{data:{category:3}}});
    }else if(this.type == 4){
      this.router.navigate(['/MealsOfCategory'], {state:{data:{category:4}}});
    }else if(this.type == 5){
      this.router.navigate(['/MealsOfCategory'], {state:{data:{category:5}}});
    }else{
      this.router.navigate(['/MealsOfCategory'], {state:{data:{category:6}}});
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
