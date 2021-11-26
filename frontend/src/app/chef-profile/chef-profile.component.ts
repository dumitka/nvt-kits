import { Component, OnInit } from '@angular/core';
import { ChefProfileService } from './chef-profile.service';
import { AuthService } from '../login/auth.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-chef-profile',
  templateUrl: './chef-profile.component.html',
  styleUrls: ['./chef-profile.component.css']
})
export class ChefProfileComponent implements OnInit {

  loggedChef:any;

  constructor(private service:ChefProfileService, private authService: AuthService, private router:Router) {
    this.service.getChef().subscribe((data:any) => {this.loggedChef = data; console.log(this.loggedChef)});

    
  }

  ngOnInit(): void {
  }



  meal(){
    this.router.navigate(['/MealCategory']);
  }


  menu(){
    this.router.navigate(['/MenuCategory']);
  }


  logout(){
    this.authService.logout();
  }
}


