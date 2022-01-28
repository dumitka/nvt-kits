import { Component, OnInit } from '@angular/core';
import { ChefProfileService } from '../service/chef-profile.service';
import { AuthService } from '../../../../login/auth.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-chef-profile',
  templateUrl: './chef-profile.component.html',
  styleUrls: ['./chef-profile.component.css']
})
export class ChefProfileComponent implements OnInit {

  loggedChefName:string;
  loggedChefLastName:string;
  loggedChefId:number;


  constructor(private service:ChefProfileService, private authService: AuthService, private router:Router) {
    this.service.getChef().subscribe((data:any) => {
      this.loggedChefName = data.name; 
      this.loggedChefLastName = data.lastName; 
      this.loggedChefId = data.id;
    });

    
  }

  ngOnInit(): void {
  }

  orders(){
    this.router.navigate(['/NewMealOrders'], {state:{data:{"isChef": true, "userId":this.loggedChefId}}});
  }

  meal(){
    this.router.navigate(['/MealCategories']);
  }


  currentMenu(){
    this.router.navigate(['/CurrentMenuCategories']);
  }

  newMenu(){
    this.service.getAllMeals().subscribe((data:any) => {
      let allMeals = data;
      this.router.navigate(['/NewMenuCategories'], {state:{data:{"newMenuMeals":[], "allMeals":allMeals}}});
    });
    
  }

  logout(){
    this.authService.logout();
  }
}


