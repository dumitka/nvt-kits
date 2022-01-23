import { Component, OnInit } from '@angular/core';
import { CookProfileService } from '../service/cook-profile.service';
import { AuthService } from '../../../../login/auth.service';

import {MatCardModule} from '@angular/material/card';
@Component({
  selector: 'app-cook-profile',
  templateUrl: './cook-profile.component.html',
  styleUrls: ['./cook-profile.component.css']
})
export class CookProfileComponent implements OnInit {

  loggedCookName:string;
  loggedCookLastName:string;

  constructor(private service:CookProfileService,  private authService: AuthService) {
    this.service.getCook().subscribe((data:any) => {
      this.loggedCookName = data.name; 
      this.loggedCookLastName = data.lastName;

      //provjera ispis
      console.log(this.loggedCookName);
      console.log(this.loggedCookLastName);
    })
  }

  ngOnInit(): void {
  }

  logout(){
    this.authService.logout();
  }
}
