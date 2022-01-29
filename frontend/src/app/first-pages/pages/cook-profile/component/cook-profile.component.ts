import { Component, OnInit } from '@angular/core';
import { CookProfileService } from '../service/cook-profile.service';
import { AuthService } from '../../../../login/auth.service';
import { Router } from '@angular/router';

import {MatCardModule} from '@angular/material/card';
@Component({
  selector: 'app-cook-profile',
  templateUrl: './cook-profile.component.html',
  styleUrls: ['./cook-profile.component.css']
})
export class CookProfileComponent implements OnInit {

  loggedCookName:string;
  loggedCookLastName:string;
  loggedCookId: number;

  constructor(private service:CookProfileService,  private authService: AuthService,  private router:Router) {
    this.service.getCook().subscribe((data:any) => {
      this.loggedCookName = data.name; 
      this.loggedCookLastName = data.lastName;
      this.loggedCookId = data.id;

      
    })
  }

  ngOnInit(): void {
  }

  orders(){
    console.log("uslo");
    this.router.navigate(['/NewMealOrdersCook'], {state:{data:{"isChef": false, "userId":this.loggedCookId}}});
  }


  logout(){
    this.authService.logout();
  }
}
