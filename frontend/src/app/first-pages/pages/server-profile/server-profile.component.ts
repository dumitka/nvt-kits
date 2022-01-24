import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../../login/auth.service';
import { UserService } from '../../services/user-service/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-server-profile',
  templateUrl: './server-profile.component.html',
  styleUrls: ['./server-profile.component.css']
})
export class ServerProfileComponent implements OnInit {
  ime:string;
  prezime:string;
  
  constructor(private service:UserService, private authService: AuthService, private router: Router) {
    this.service.getInfo().subscribe((data:any) => {
      this.ime = data.name; 
      this.prezime = data.lastName; 
    });
  }

  ngOnInit(): void {
  }
  
  redirektujSefSale(){
    console.log("redirekt sef sale");
    this.router.navigate(['/ServerFirstPage']);
  }
  
  redirektujKonobar(){
    console.log("redirekt konobar");
    this.router.navigate(['/WaiterProfileServer']);
  }

  odjava(){
    this.authService.logout();
  }
}
