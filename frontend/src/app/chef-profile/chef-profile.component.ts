import { Component, OnInit } from '@angular/core';
import { ChefProfileService } from './chef-profile.service';
import { AuthService } from '../login/auth.service';

@Component({
  selector: 'app-chef-profile',
  templateUrl: './chef-profile.component.html',
  styleUrls: ['./chef-profile.component.css']
})
export class ChefProfileComponent implements OnInit {

  loggedChef:any;

  constructor(private service:ChefProfileService, private authService: AuthService) {
    this.service.getChef().subscribe((data:any) => {this.loggedChef = data; console.log(this.loggedChef)});

    
  }

  ngOnInit(): void {
  }

  logout(){
    this.authService.logout();
  }
}
