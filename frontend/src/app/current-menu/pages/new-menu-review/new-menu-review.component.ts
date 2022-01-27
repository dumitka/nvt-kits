import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CurrentMenuService } from '../../service/current-menu.service';
import { MatDialog } from '@angular/material/dialog';
import { AreYouSureDialogComponent } from '../../component/are-you-sure-dialog/are-you-sure-dialog.component';
import { MatSnackBar, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import { DeleteMealDialogComponent } from 'src/app/meal-category/pages/delete-meal/delete-meal-dialog.component';
import { SaveMenuDialogComponent } from '../../component/save-menu-dialog/save-menu-dialog.component';


@Component({
  selector: 'app-new-menu-review',
  templateUrl: './new-menu-review.component.html',
  styleUrls: ['./new-menu-review.component.css']
})
export class NewMenuReviewComponent implements OnInit {
  listOfMeals:any;
  position:number = 0;
  displayedColumns: string[] = ['position', 'name', 'description', 'price'];

  newMenuMeals: any = [];
  allMeals: any = [];


  RESPONSE_OK : number = 0;
  RESPONSE_ERROR : number = -1;
  verticalPosition: MatSnackBarVerticalPosition = "top";

  constructor(public service: CurrentMenuService, private router:Router, private dialog:MatDialog, 
    private snackBar: MatSnackBar) { 
    this.newMenuMeals = history.state.data.newMenuMeals;
    this.allMeals = history.state.data.allMeals;
    this.listOfMeals = this.newMenuMeals;
  }

  ngOnInit(): void {
  }


  deleteMeal(meal:any){
    let dialogReturnValue = this.dialog.open(DeleteMealDialogComponent, {
      height: '30%',
      width: '45%',
    });

    dialogReturnValue.afterClosed().subscribe(returnValue => {
      if(returnValue == "true"){
        this.allMeals.push(meal.mealDTO);
        this.listOfMeals = this.newMenuMeals.filter(elem => elem.mealDTO.id != meal.mealDTO.id); 
        this.router.navigate(['/NewMenuReview'],  {state:{data:{"newMenuMeals":this.listOfMeals, "allMeals":this.allMeals}}});
      }
    });
  }


  save(){
    let dialogReturnValue = this.dialog.open(SaveMenuDialogComponent, {
      height: '30%',
      width: '45%',
    });

    dialogReturnValue.afterClosed().subscribe(returnValue => {
      if(returnValue == "true"){
        this.service.newMenu(this.newMenuMeals).subscribe(
          response => {
              this.openSnackBar("Novi meni je postavljen! Uživajte :)", this.RESPONSE_OK);
              this.router.navigate(['/ChefProfile']);
          },
          error => {
            this.openSnackBar("Došlo je do nekakvog problema, molim Vas pokušajte ponovo kasnije! :)", this.RESPONSE_ERROR);
          }
        )
      }
    });

  }


  quit(){
    let dialogReturnValue = this.dialog.open(AreYouSureDialogComponent, {
      height: '30%',
      width: '45%',
    });

    dialogReturnValue.afterClosed().subscribe(returnValue => {
      if(returnValue == "true"){
        this.router.navigate(['/ChefProfile']);
      }
    });
  }


  back(){
    this.router.navigate(['/NewMenuCategories'],  {state:{data:{"newMenuMeals":this.newMenuMeals, "allMeals":this.allMeals}}});
  }


  openSnackBar(msg: string, responseCode: number) {
    this.snackBar.open(msg, "x", {
      duration: responseCode === this.RESPONSE_OK ? 3000 : 20000,
      verticalPosition: this.verticalPosition,
      panelClass: responseCode === this.RESPONSE_OK ? "back-green" : "back-red"
    });
  }

}
