import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../../login/auth.service';
import { ServerServiceService } from '../../services/server-service/server-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-server-profile',
  templateUrl: './server-profile.component.html',
  styleUrls: ['./server-profile.component.css']
})
export class ServerProfileComponent implements OnInit {
  firstName:string;
  lastName:string;
  
  constructor(private service:ServerServiceService, private authService: AuthService, private router:Router) {
    this.service.getInfo().subscribe((data:any) => {
      this.firstName = data.name; 
      this.lastName = data.lastName; 
    });
  }

  ngOnInit(): void {
  }
  
  redirektujSS(){
    console.log("redirekti sef sale");
  }
  
  redirektujK(){
    console.log("redirekti konobar");
  }

  odjava(){
    this.authService.logout();
  }
}
