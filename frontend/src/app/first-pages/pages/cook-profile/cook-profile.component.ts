import { Component, OnInit } from '@angular/core';
import { CookProfileService } from './cook-profile.service';
import { AuthService } from '../../../login/auth.service';

@Component({
  selector: 'app-cook-profile',
  templateUrl: './cook-profile.component.html',
  styleUrls: ['./cook-profile.component.css']
})
export class CookProfileComponent implements OnInit {

  loggedCook:any;

  constructor(private service:CookProfileService,  private authService: AuthService) {
    this.service.getCook().subscribe((data:any) => {this.loggedCook = data; console.log(this.loggedCook)})
  }

  ngOnInit(): void {
  }

  logout(){
    this.authService.logout();
  }
}
