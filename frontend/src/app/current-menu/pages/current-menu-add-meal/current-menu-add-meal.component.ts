import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CurrentMenuService } from '../../service/current-menu.service';
import { MatDialog } from '@angular/material/dialog';
import { DeleteMealDialogComponent } from '../../../meal-category/pages/delete-meal/delete-meal-dialog.component';
import { MatSnackBar, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import { ChangeMealPriceDialogComponent } from '../../component/change-meal-price-dialog/change-meal-price-dialog.component';

@Component({
  selector: 'app-current-menu-add-meal',
  templateUrl: './current-menu-add-meal.component.html',
  styleUrls: ['./current-menu-add-meal.component.css']
})
export class CurrentMenuAddMealComponent implements OnInit {

  listOfMeals:any;
  position:number = 0;
  displayedColumns: string[] = ['position', 'name', 'description'];

  
  RESPONSE_OK : number = 0;
  RESPONSE_ERROR : number = -1;
  verticalPosition: MatSnackBarVerticalPosition = "top";

  category:number;

  constructor(private router:Router, private dialog:MatDialog, private service:CurrentMenuService, private snackBar: MatSnackBar) { 
    this.category = history.state.data.category;
    this.service.getMealsNotInCurrent().subscribe((data:any) =>{
      this.listOfMeals = data;
    })
  }

  ngOnInit(): void {
  }



  addPrice(meal:any){
    let newPrice:number = -1;

    let dialogReturnValue = this.dialog.open(ChangeMealPriceDialogComponent, {
      height: '30%',
      width: '45%',
      data: {"newPrice": newPrice},
    })

    dialogReturnValue.afterClosed().subscribe(result => {
      let newPriceReturnValue = result;

      if(newPriceReturnValue > 0){

        let sendData:any = {"id": -1, "mealDTO":meal, "price":newPriceReturnValue};

        console.log(sendData);

        this.service.addMeal(sendData).subscribe(
          response => {

            this.openSnackBar("Uspešno ste izmenili cenu", this.RESPONSE_OK);
            if(meal.type =="COLD_APPETIZER"){
              this.router.navigate(['/CurrentMenuMeals'], {state:{data:{category:1}}});
            }else if(meal.type =="HOT_APPETIZER"){
              this.router.navigate(['/CurrentMenuMeals'], {state:{data:{category:2}}});
            }else if(meal.type =="MAIN_COURSE"){
              this.router.navigate(['/CurrentMenuMeals'], {state:{data:{category:3}}});
            }else if(meal.type =="DESERT"){
              this.router.navigate(['/CurrentMenuMeals'], {state:{data:{category:4}}});
            }else if(meal.type =="SALAD"){
              this.router.navigate(['/CurrentMenuMeals'], {state:{data:{category:5}}});
            }else{
              this.router.navigate(['/CurrentMenuMeals'], {state:{data:{category:6}}});
            }
            
        },
        error => {
          this.openSnackBar("Došlo je do nekakve greške. Molim, pokušajte kasnije :)", this.RESPONSE_ERROR);
        }
        );
      }else{
        this.openSnackBar("Morate uneti cenu koja je veća od 0! :)", this.RESPONSE_ERROR);
      }

    });
  }


  back(){
    this.router.navigate(['/CurrentMenuMeals']);
    if(this.category == 1){
      this.router.navigate(['/CurrentMenuMeals'], {state:{data:{category:1}}});
    }else if(this.category == 2){
      this.router.navigate(['/CurrentMenuMeals'], {state:{data:{category:2}}});
    }else if(this.category ==3){
      this.router.navigate(['/CurrentMenuMeals'], {state:{data:{category:3}}});
    }else if(this.category ==4){
      this.router.navigate(['/CurrentMenuMeals'], {state:{data:{category:4}}});
    }else if(this.category ==5){
      this.router.navigate(['/CurrentMenuMeals'], {state:{data:{category:5}}});
    }else{
      this.router.navigate(['/CurrentMenuMeals'], {state:{data:{category:6}}});
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
