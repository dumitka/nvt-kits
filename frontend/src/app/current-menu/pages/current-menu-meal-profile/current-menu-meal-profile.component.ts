import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { DeleteMealDialogComponent } from '../../../meal-category/pages/delete-meal/delete-meal-dialog.component';
import { MatSnackBar, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import { CurrentMenuService } from '../../service/current-menu.service';
import { ChangeMealPriceDialogComponent } from '../../component/change-meal-price-dialog/change-meal-price-dialog.component';

@Component({
  selector: 'app-current-menu-meal-profile',
  templateUrl: './current-menu-meal-profile.component.html',
  styleUrls: ['./current-menu-meal-profile.component.css']
})
export class CurrentMenuMealProfileComponent implements OnInit {

  mealPrice:any;
  slika: string = "food-profile.jpg";

  mealHasItsOwnPicture : boolean = false;
  mealDoesNotHaveItsOwnPicture:boolean = false;

  prepDifficulty:string;

  RESPONSE_OK : number = 0;
  RESPONSE_ERROR : number = -1;
  verticalPosition: MatSnackBarVerticalPosition = "top";


  constructor(private router:Router, private dialog:MatDialog, private service:CurrentMenuService, private snackBar: MatSnackBar) { 
    this.mealPrice = history.state.data.mealPrice;

    if(this.mealPrice.mealDTO.image != "nema") this.slika = this.mealPrice.mealDTO.image;

    if(this.mealPrice.mealDTO.mealDifficulty == "EASY"){
      this.prepDifficulty = "LAKO";
    }else if(this.mealPrice.mealDTO.mealDifficulty == "MEDIUM"){
      this.prepDifficulty = "SREDNJE";
    }else{
      this.prepDifficulty = "TESKO";
    }

    console.log(this.mealPrice);
  }



  ngOnInit(): void {
  }


  change(){
    let newPrice:number = -1;

    let dialogReturnValue = this.dialog.open(ChangeMealPriceDialogComponent, {
      height: '30%',
      width: '45%',
      data: {"newPrice": newPrice},
    })

    dialogReturnValue.afterClosed().subscribe(result => {
      let newPriceReturnValue = result;
      console.log("Printa --> " + newPriceReturnValue);

      if(newPriceReturnValue == 9376193218740986283){

      }else if(newPriceReturnValue > 0){
        this.mealPrice.price = newPriceReturnValue;
        console.log(this.mealPrice);

        this.service.change(this.mealPrice).subscribe(
          response => {

            
            this.openSnackBar("Uspešno ste izmenili cenu", this.RESPONSE_OK);
            if(this.mealPrice.mealDTO.type =="COLD_APPETIZER"){
              this.router.navigate(['/CurrentMenuMeals'], {state:{data:{category:1}}});
            }else if(this.mealPrice.mealDTO.type =="HOT_APPETIZER"){
              this.router.navigate(['/CurrentMenuMeals'], {state:{data:{category:2}}});
            }else if(this.mealPrice.mealDTO.type =="MAIN_COURSE"){
              this.router.navigate(['/CurrentMenuMeals'], {state:{data:{category:3}}});
            }else if(this.mealPrice.mealDTO.type =="DESERT"){
              this.router.navigate(['/CurrentMenuMeals'], {state:{data:{category:4}}});
            }else if(this.mealPrice.mealDTO.type =="SALAD"){
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
        this.openSnackBar("Morate uneti cenu veću od 0! :)", this.RESPONSE_ERROR); 
      }

    });
  }



  delete(){
    let dialogReturnValue = this.dialog.open(DeleteMealDialogComponent, {
      height: '30%',
      width: '45%',
    })

    dialogReturnValue.afterClosed().subscribe(returnValue => {
      if(returnValue == "true"){
        this.service.delete(this.mealPrice).subscribe(
          response => {
              this.openSnackBar("Uspešno ste obrisali jelo", this.RESPONSE_OK);
              
              if(this.mealPrice.mealDTO.type =="COLD_APPETIZER"){
                this.router.navigate(['/CurrentMenuMeals'], {state:{data:{category:1}}});
              }else if(this.mealPrice.mealDTO.type =="HOT_APPETIZER"){
                this.router.navigate(['/CurrentMenuMeals'], {state:{data:{category:2}}});
              }else if(this.mealPrice.mealDTO.type =="MAIN_COURSE"){
                this.router.navigate(['/CurrentMenuMeals'], {state:{data:{category:3}}});
              }else if(this.mealPrice.mealDTO.type =="DESERT"){
                this.router.navigate(['/CurrentMenuMeals'], {state:{data:{category:4}}});
              }else if(this.mealPrice.mealDTO.type =="SALAD"){
                this.router.navigate(['/CurrentMenuMeals'], {state:{data:{category:5}}});
              }else{
                this.router.navigate(['/CurrentMenuMeals'], {state:{data:{category:6}}});
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
    if(this.mealPrice.mealDTO.type =="COLD_APPETIZER"){
      this.router.navigate(['/CurrentMenuMeals'], {state:{data:{category:1}}});
    }else if(this.mealPrice.mealDTO.type =="HOT_APPETIZER"){
      this.router.navigate(['/CurrentMenuMeals'], {state:{data:{category:2}}});
    }else if(this.mealPrice.mealDTO.type =="MAIN_COURSE"){
      this.router.navigate(['/CurrentMenuMeals'], {state:{data:{category:3}}});
    }else if(this.mealPrice.mealDTO.type =="DESERT"){
      this.router.navigate(['/CurrentMenuMeals'], {state:{data:{category:4}}});
    }else if(this.mealPrice.mealDTO.type =="SALAD"){
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
