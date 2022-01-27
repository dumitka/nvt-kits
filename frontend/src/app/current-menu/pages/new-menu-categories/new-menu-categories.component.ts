import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { AreYouSureDialogComponent } from '../../component/are-you-sure-dialog/are-you-sure-dialog.component';


@Component({
  selector: 'app-new-menu-categories',
  templateUrl: './new-menu-categories.component.html',
  styleUrls: ['./new-menu-categories.component.css']
})
export class NewMenuCategoriesComponent implements OnInit {

  newMenuMeals: any = [];
  allMeals: any = [];

  constructor(private router:Router, private dialog:MatDialog,) {
    this.allMeals = history.state.data.allMeals;
    this.newMenuMeals = history.state.data.newMenuMeals;

  }

  ngOnInit(): void {
  }



  //salji

  getColdAppetizers(){
    this.router.navigate(['/NewMenuMeals'], {state:{data:{category:1, "newMenuMeals":this.newMenuMeals, "allMeals":this.allMeals}}});
  }

  getHotAppetizers(){
    this.router.navigate(['/NewMenuMeals'], {state:{data:{category:2, "newMenuMeals":this.newMenuMeals, "allMeals":this.allMeals}}});
  }


  getMainMeals(){
    this.router.navigate(['/NewMenuMeals'], {state:{data:{category:3, "newMenuMeals":this.newMenuMeals, "allMeals":this.allMeals}}});
  }


  getDesserts(){
    this.router.navigate(['/NewMenuMeals'], {state:{data:{category:4, "newMenuMeals":this.newMenuMeals, "allMeals":this.allMeals}}});
  }


  getSalats(){
    this.router.navigate(['/NewMenuMeals'], {state:{data:{category:5, "newMenuMeals":this.newMenuMeals, "allMeals":this.allMeals}}});
  }


  getAppendices(){
    this.router.navigate(['/NewMenuMeals'], {state:{data:{category:6, "newMenuMeals":this.newMenuMeals, "allMeals":this.allMeals}}});
  }


  returnToProfile(){
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



  review(){
    this.router.navigate(['/NewMenuReview'],  {state:{data:{"newMenuMeals":this.newMenuMeals, "allMeals":this.allMeals}}});
  }
}
