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


  constructor(private service:ChefProfileService, private authService: AuthService, private router:Router) {
    this.service.getChef().subscribe((data:any) => {
      this.loggedChefName = data.name; 
      this.loggedChefLastName = data.lastName; 
      
      //provjera
      console.log(this.loggedChefName);
      console.log(this.loggedChefLastName);
    });

    
  }

  ngOnInit(): void {
  }



  meal(){
    this.router.navigate(['/MealCategories']);
  }


  menu(){
    this.router.navigate(['/MenuCategory']);
  }


  logout(){
    this.authService.logout();
  }
}

