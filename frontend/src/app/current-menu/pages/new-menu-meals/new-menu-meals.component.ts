import { Component, OnInit } from '@angular/core';
import { MatSnackBar, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import { ChangeMealPriceDialogComponent } from '../../component/change-meal-price-dialog/change-meal-price-dialog.component';
import { Router } from '@angular/router';
import { CurrentMenuService } from '../../service/current-menu.service';
import { MatDialog } from '@angular/material/dialog';




@Component({
  selector: 'app-new-menu-meals',
  templateUrl: './new-menu-meals.component.html',
  styleUrls: ['./new-menu-meals.component.css']
})
export class NewMenuMealsComponent implements OnInit {

  listOfMeals:any;
  position:number = 0;
  displayedColumns: string[] = ['position', 'name', 'description'];



  
  RESPONSE_OK : number = 0;
  RESPONSE_ERROR : number = -1;
  verticalPosition: MatSnackBarVerticalPosition = "top";

  category:number;
  title:string;

  newMenuMeals: any = [];
  allMeals: any = [];


  constructor(private router:Router, private dialog:MatDialog, private service:CurrentMenuService, private snackBar: MatSnackBar) { 
    this.newMenuMeals = history.state.data.newMenuMeals;
    this.allMeals = history.state.data.allMeals;
    this.category = history.state.data.category;

    
    if(this.category == 1){
      this.listOfMeals = this.allMeals.filter(meal => meal.type == "COLD_APPETIZER" && meal.deleted==false);
      this.title = "HLADNA PREDJELA";
      
    }else if(this.category == 2){
      this.listOfMeals = this.allMeals.filter(meal => meal.type == "HOT_APPETIZER" && meal.deleted==false);
      this.title = "TOPLA PREDJELA";

    }else if(this.category == 3){
      this.listOfMeals = this.allMeals.filter(meal => meal.type == "MAIN_COURSE" && meal.deleted==false);
      this.title = "GLAVNA JELA";

    }else if(this.category == 4){
      this.listOfMeals = this.allMeals.filter(meal => meal.type == "DESERT" && meal.deleted==false);
      this.title = "DESERTI";

    }else if(this.category == 5){
      this.listOfMeals = this.allMeals.filter(meal => meal.type == "SALAD" && meal.deleted==false);
      this.title = "SALATE";

    }else{
      this.listOfMeals = this.allMeals.filter(meal => meal.type == "APPENDICES" && meal.deleted==false);
      this.title = "DODACI";
    }
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
      console.log("Printa --> " + newPriceReturnValue);

      if(newPriceReturnValue == 9376193218740986283 ){
        
      }else if(newPriceReturnValue > 0){
        let element = {"mealDTO":meal, "price":newPriceReturnValue}
        this.newMenuMeals.push(element);

        let restOfMeals = this.allMeals.filter(elem => elem.id != meal.id); 
        this.router.navigate(['/NewMenuCategories'],  {state:{data:{"newMenuMeals":this.newMenuMeals, "allMeals":restOfMeals}}});
        this.openSnackBar("Uspešno ste dodali jelo u novi meni. :)", this.RESPONSE_OK);     
      }else{
        this.openSnackBar("Morate uneti cenu veću od 0! :)", this.RESPONSE_ERROR); 
      }

    })
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
