import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { DeleteMealDialogComponent } from '../delete-meal/delete-meal-dialog.component';
import { MealServiceService } from '../../service/meal-service.service';
import { MatSnackBar, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';

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

  RESPONSE_OK : number = 0;
  RESPONSE_ERROR : number = -1;
  verticalPosition: MatSnackBarVerticalPosition = "top";


  constructor(private router:Router, private dialog:MatDialog, private service:MealServiceService, private snackBar: MatSnackBar,) { 
    this.meal = history.state.data.meal;

    if(this.meal.image != "nema" || this.meal.image == null){
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
    this.router.navigate(['/ChangeMeal'], {state:{data:{"meal":this.meal}}});
  }


  delete(){
    let dialogReturnValue = this.dialog.open(DeleteMealDialogComponent, {
      height: '30%',
      width: '45%',
    });

    dialogReturnValue.afterClosed().subscribe(returnValue => {
      
      if(returnValue == "true"){
        this.service.deleteMeal(this.meal).subscribe(
          response => {
              this.openSnackBar("Uspešno ste obrisali jelo", this.RESPONSE_OK);
              
              if(this.meal.type =="COLD_APPETIZER"){
                this.router.navigate(['/MealsOfCategory'], {state:{data:{category:1}}});
              }else if(this.meal.type =="HOT_APPETIZER"){
                this.router.navigate(['/MealsOfCategory'], {state:{data:{category:2}}});
              }else if(this.meal.type =="MAIN_COURSE"){
                this.router.navigate(['/MealsOfCategory'], {state:{data:{category:3}}});
              }else if(this.meal.type =="DESERT"){
                this.router.navigate(['/MealsOfCategory'], {state:{data:{category:4}}});
              }else if(this.meal.type =="SALAD"){
                this.router.navigate(['/MealsOfCategory'], {state:{data:{category:5}}});
              }else{
                this.router.navigate(['/MealsOfCategory'], {state:{data:{category:6}}});
              }
          },
          error => {
            this.openSnackBar("Došlo je do nekakvog problema, molim Vas pokušajte ponovo kasnije! :)", this.RESPONSE_ERROR);
          }
        )
      }

    });
  }

  

  returnButton(){
    if(this.meal.type =="COLD_APPETIZER"){
      this.router.navigate(['/MealsOfCategory'], {state:{data:{category:1}}});
    }else if(this.meal.type =="HOT_APPETIZER"){
      this.router.navigate(['/MealsOfCategory'], {state:{data:{category:2}}});
    }else if(this.meal.type =="MAIN_COURSE"){
      this.router.navigate(['/MealsOfCategory'], {state:{data:{category:3}}});
    }else if(this.meal.type =="DESERT"){
      this.router.navigate(['/MealsOfCategory'], {state:{data:{category:4}}});
    }else if(this.meal.type =="SALAD"){
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
