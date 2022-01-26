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

  verticalPosition: MatSnackBarVerticalPosition = "top";



  constructor(private formBuilder: FormBuilder, 
                      private service:MealServiceService,
                      private snackBar: MatSnackBar,
                      private router:Router) {

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
       this.router.navigate(['/MealsOfCategory'], {state:{data:{category:3}}});
      },
      error => {
        this.openSnackBar("Jelo sa datim imenom i opisom već postoji. Dodajte novo jelo. :)", this.RESPONSE_ERROR);
      }
    )
  }



  openSnackBar(msg: string, responseCode: number) {
    this.snackBar.open(msg, "x", {
      duration: responseCode === this.RESPONSE_OK ? 3000 : 20000,
      verticalPosition: this.verticalPosition,
      panelClass: responseCode === this.RESPONSE_OK ? "back-green" : "back-red"
    });
  }





}