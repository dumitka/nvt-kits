import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CurrentMenuService } from '../../service/current-menu.service';

@Component({
  selector: 'app-current-menu-meals',
  templateUrl: './current-menu-meals.component.html',
  styleUrls: ['./current-menu-meals.component.css']
})
export class CurrentMenuMealsComponent implements OnInit {

  category:number;
  listOfMeals:any;
  position:number = 0;
  displayedColumns: string[] = ['position', 'name', 'description', 'price'];

  title:string;

  constructor(public service: CurrentMenuService, private router:Router) {
    this.category = history.state.data.category;


    if(this.category == 1){
      console.log("Usla");
      this.service.getColdAppetizers().subscribe((data:any) =>{
        console.log(data);
        this.listOfMeals = data;
        this.title = "HLADNA PREDJELA";
      })
      
    }else if(this.category == 2){
      this.service.getHotAppetizer().subscribe((data:any) =>{
        this.listOfMeals = data;
        this.title = "TOPLA PREDJELA";
      })

    }else if(this.category == 3){
      this.service.getMainCourse().subscribe((data:any) =>{
        this.listOfMeals = data;
        this.title = "GLAVNA JELA";
      })

    }else if(this.category == 4){
      this.service.getDesert().subscribe((data:any) =>{
        this.listOfMeals = data;
        this.title = "DESERTI";
      })

    }else if(this.category == 5){
      this.service.getSalad().subscribe((data:any) =>{
        this.listOfMeals = data;
        this.title = "SALATE";
      })

    }else{
      this.service.getAppendices().subscribe((data:any) =>{
        this.listOfMeals = data;
        this.title = "DODACI";
      })

    }
    
    
  }

  ngOnInit(): void {
  }

  add(){
    this.router.navigate(['/CurrentMenuAddMeal']);
  }


  back(){
    this.router.navigate(['/CurrentMenuCategories']);
  }


  mealProfile(meal){
    this.router.navigate(['/CurrentMenuMealProfile']);
  }


  returnToProfile(){
    this.router.navigate(['/ChefProfile']);
  }
}
