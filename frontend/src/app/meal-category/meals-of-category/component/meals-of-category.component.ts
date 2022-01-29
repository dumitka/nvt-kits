import { Component, OnInit } from '@angular/core';
import { MealsOfCategoryService } from '../service/meals-of-category.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-meals-of-category',
  templateUrl: './meals-of-category.component.html',
  styleUrls: ['./meals-of-category.component.css']
})
export class MealsOfCategoryComponent implements OnInit {

  category:number;
  listOfMeals:any;
  position:number = 0;
  displayedColumns: string[] = ['position', 'name', 'description'];

  title:string;

  constructor(public service: MealsOfCategoryService, private router:Router) {
    this.category = history.state.data.category;


    if(this.category == 1){
      console.log("Usla");
      this.service.getColdAppetizers().subscribe((data:any) =>{
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


  mealProfile(meal:any){
    this.router.navigate(['/MealProfile'], {state:{data:{"meal":meal}}});
  }


  add(){
    this.router.navigate(['/NewMeal'], {state:{data:{"type":this.category}}});
  }


  return(){
    this.router.navigate(['/MealCategories']);
  }
}
