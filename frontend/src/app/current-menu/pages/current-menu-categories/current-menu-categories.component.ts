import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-current-menu-categories',
  templateUrl: './current-menu-categories.component.html',
  styleUrls: ['./current-menu-categories.component.css']
})
export class CurrentMenuCategoriesComponent implements OnInit {

  constructor(private router:Router) { }

  ngOnInit(): void {
  }



  //salji

  getColdAppetizers(){
    this.router.navigate(['/CurrentMenuMeals'], {state:{data:{category:1}}});
  }

  getHotAppetizers(){
    this.router.navigate(['/CurrentMenuMeals'], {state:{data:{category:2}}});
  }


  getMainMeals(){
    this.router.navigate(['/CurrentMenuMeals'], {state:{data:{category:3}}});
  }


  getDesserts(){
    this.router.navigate(['/CurrentMenuMeals'], {state:{data:{category:4}}});
  }


  getSalats(){
    this.router.navigate(['/CurrentMenuMeals'], {state:{data:{category:5}}});
  }


  getAppendices(){
    this.router.navigate(['/CurrentMenuMeals'], {state:{data:{category:6}}});
  }


  returnToProfile(){
    this.router.navigate(['/ChefProfile']);
  }
}
