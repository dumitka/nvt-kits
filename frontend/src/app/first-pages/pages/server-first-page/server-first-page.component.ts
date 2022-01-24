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

  constructor(private service:UserService, private authService: AuthService, private router: Router, private drinkService: DrinkService,) {
    //nekako poslati preko konstruktora podatke da ne trazi opet
    this.service.getInfo().subscribe((data:any) => {
      this.ime = data.name; 
      this.prezime = data.lastName; 
    });
  }

  ngOnInit(): void {
  }

  dodajPice(){
    console.log("dodaj novo pice");
    this.router.navigate(["/AddDrink"]);

    var novoPice = {
      "id": 12,
      "name": "Piceee",
      "type": 1,
      "description": "Piceee",
      "amountNumber": 12,
      "amountUnit": "l",
      "available": true,
      "image": "Piceee.jpg",
    };
    var odgovor = this.drinkService.editDrink(novoPice);
    console.log(odgovor);
  }
  
  svaPica(){
    console.log("pregled svih pica");
    this.router.navigate(["/AllDrinks"]);
  }

  dodajKP(){
    console.log("dodaj novu kartu pica");
    //this.router.navigate(["/AllDrinks"]);   // ako budem mogla da namestim parametre
  }
  
  kartaPica(){
    console.log("pregled karte pica");
    this.router.navigate(["/DrinkCard"]);
  }

  odjava(){
    this.authService.logout();
  }
}
