import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../../login/auth.service';
import { UserService } from '../../services/user-service/user.service';
import { DrinkService } from '../../../drinks/services/drink-service/drink.service';

@Component({
  selector: 'app-server-first-page',
  templateUrl: './server-first-page.component.html',
  styleUrls: ['./server-first-page.component.css']
})
export class ServerFirstPageComponent implements OnInit {
  ime:string;
  prezime:string;

  constructor(private service:UserService, private authService: AuthService, private ruter: Router, private drinkService: DrinkService,) {
    this.service.getInfo().subscribe((data:any) => {
      this.ime = data.name; 
      this.prezime = data.lastName; 
    });
  }

  ngOnInit(): void {
  }

  dodajPice(){
    this.ruter.navigate(["/AddDrink"]);
  }
  
  svaPica(){
    this.ruter.navigate(["/AllDrinks"]);
  }

  dodajKP(){
    //this.ruter.navigate(["/AllDrinks"]);   // ako budem mogla da namestim parametre
  }
  
  kartaPica(){
    this.ruter.navigate(["/DrinkCard"]);
  }

  odjava(){
    this.authService.logout();
  }
}
