import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MealServiceService } from '../../service/meal-service.service';
import { MatSnackBar, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { analyzeAndValidateNgModules } from '@angular/compiler';
import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';


@Component({
  selector: 'app-change-meal',
  templateUrl: './change-meal.component.html',
  styleUrls: ['./change-meal.component.css']
})
export class ChangeMealComponent implements OnInit {

  public changeMealForm: FormGroup;

  RESPONSE_OK : number = 0;
  RESPONSE_ERROR : number = -1;

  meal:any;
  changedMeal:any;

  verticalPosition: MatSnackBarVerticalPosition = "top";



  constructor(private formBuilder: FormBuilder, 
    private service:MealServiceService,
    private snackBar: MatSnackBar,
    private router:Router) {
      this.meal = history.state.data.meal;

      this.changeMealForm = this.formBuilder.group({
      id:[this.meal.id],
      name:[this.meal.name, Validators.required],
      description:['', Validators.required],
      type:['', Validators.required],
      timePreparation:['', [Validators.required, Validators.min(1)]], 
      mealDifficulty:['', Validators.required],
      amountNumber: ['', [Validators.required, Validators.min(1)]],
      amountUnit: ['', Validators.required],
      image: ['nema']
      });

      this.fillDataForm();

  }



  ngOnInit(): void {
  }



  fillDataForm() {
    this.changeMealForm.get('description')?.setValue(this.meal.description);
    this.changeMealForm.get('type')?.setValue(this.meal.type);
    this.changeMealForm.get('timePreparation')?.setValue(this.meal.timePreparation);
    this.changeMealForm.get('mealDifficulty')?.setValue(this.meal.mealDifficulty);
    this.changeMealForm.get('amountNumber')?.setValue(this.meal.amountNumber);
    this.changeMealForm.get('amountUnit')?.setValue(this.meal.amountUnit);
  }


  public requiredError = (controlName: string, errorName: string) =>{
    return this.changeMealForm.controls[controlName].hasError(errorName);
  }


  public mindError = (controlName: string, errorName: string) =>{
    return this.changeMealForm.controls[controlName].hasError(errorName);
  }


  public changeMeal(){
    console.log(this.changeMealForm.value);
    
    this.service.changeMeal(this.changeMealForm.value).subscribe(
      response => {
          this.openSnackBar("Uspešno ste izmenili jelo", this.RESPONSE_OK);

          this.service.getMeal(this.meal.id).subscribe((data:any) => {
              this.changeMeal = data;
              if(data.type == "COLD_APPETIZER"){
                this.router.navigate(['/MealsOfCategory'], {state:{data:{category:1}}});
              }else if(data.type == "HOT_APPETIZER"){
                this.router.navigate(['/MealsOfCategory'], {state:{data:{category:2}}});
              }else if(data.type == "MAIN_COURSE"){
                this.router.navigate(['/MealsOfCategory'], {state:{data:{category:3}}});
              }else if(data.type == "DESERT"){
                this.router.navigate(['/MealsOfCategory'], {state:{data:{category:4}}});
              }else if(data.type == "SALAD"){
                this.router.navigate(['/MealsOfCategory'], {state:{data:{category:5}}});
              }else{
                this.router.navigate(['/MealsOfCategory'], {state:{data:{category:6}}});
              }
              
           });
      },
      error => {
        this.openSnackBar("Jelo sa tim imenom i takvim opisom već postoje :)", this.RESPONSE_ERROR);
      }
    )
  }



  back(){
    this.router.navigate(['/MealProfile'], {state:{data:{meal:this.meal}}});
  }


  openSnackBar(msg: string, responseCode: number) {
    this.snackBar.open(msg, "x", {
      duration: responseCode === this.RESPONSE_OK ? 3000 : 20000,
      verticalPosition: this.verticalPosition,
      panelClass: responseCode === this.RESPONSE_OK ? "back-green" : "back-red"
    });
  }

}
