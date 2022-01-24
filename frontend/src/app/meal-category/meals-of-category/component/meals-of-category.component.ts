import { Component, OnInit } from '@angular/core';
import { MealsOfCategoryService } from '../service/meals-of-category.service';

@Component({
  selector: 'app-meals-of-category',
  templateUrl: './meals-of-category.component.html',
  styleUrls: ['./meals-of-category.component.css']
})
export class MealsOfCategoryComponent implements OnInit {

  category:number;
  listOfMeals:any;

  constructor(public service: MealsOfCategoryService,) {
    this.category = history.state.data.category;


    if(this.category == 1){
      console.log("Usla");
      this.service.getColdAppetizers().subscribe((data:any) =>{
        this.listOfMeals = data;
        console.log(data);
        console.log("-----------------------------")
        console.log(this.listOfMeals);
      })
      
    }else if(this.category == 2){
      this.service.getHotAppetizer().subscribe((data:any) =>{
        this.listOfMeals = data;
      })

    }else if(this.category == 3){
      this.service.getMainCourse().subscribe((data:any) =>{
        this.listOfMeals = data;
      })

    }else if(this.category == 4){
      this.service.getDesert().subscribe((data:any) =>{
        this.listOfMeals = data;
      })

    }else if(this.category == 5){
      this.service.getSalad().subscribe((data:any) =>{
        this.listOfMeals = data;
      })

    }else{
      this.service.getAppendices().subscribe((data:any) =>{
        this.listOfMeals = data;
      })

    }
    
    
  }

  ngOnInit(): void {
  }

}
