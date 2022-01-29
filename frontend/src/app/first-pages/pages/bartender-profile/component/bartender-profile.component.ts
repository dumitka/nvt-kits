import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/login/auth.service';
import { BartenderProfileService } from '../service/bartender-profile.service';

@Component({
  selector: 'app-bartender-profile',
  templateUrl: './bartender-profile.component.html',
  styleUrls: ['./bartender-profile.component.css']
})
export class BartenderProfileComponent implements OnInit {

  loggedBartenderName: string;
  loggedBartenderLastName: string;
  loggedBartenderId: number;
  
  constructor(
    private service: BartenderProfileService,
    private authService: AuthService,
    private router: Router
  ) { 


    this.service.getBartender().subscribe((data: any) => {
      this.loggedBartenderName = data.name;
      this.loggedBartenderLastName = data.lastName;
      this.loggedBartenderId = data.id;

      console.log(this.loggedBartenderName);
      console.log(this.loggedBartenderLastName);
    })
  }


  ngOnInit(): void {
  }

  goToOrders() : void {
    console.log("ajmo rutirati")
    this.router.navigate(['/NewDrinkOrders'],  {state:{data:{"isChef": false, "userId":this.loggedBartenderId}}});
  }
  modifyLayout(): void {
    this.router.navigate(['/ModifyLayout']);
  }

  logout(){
    this.authService.logout();
  }
}
