import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/login/auth.service';
import { DirectorProfileService } from '../service/director-profile.service';

@Component({
  selector: 'app-director-profile',
  templateUrl: './director-profile.component.html',
  styleUrls: ['./director-profile.component.css']
})
export class DirectorProfileComponent implements OnInit {

  loggedDirectorName: string;
  loggedDirectorLastName: string;

  constructor(
    private service: DirectorProfileService, 
    private authService: AuthService,
    private router: Router) {

    this.service.getDirector().subscribe((data: any) => {
      this.loggedDirectorName = data.name;
      this.loggedDirectorLastName = data.lastName;

      console.log(this.loggedDirectorName);
      console.log(this.loggedDirectorLastName);
    })


    }

  ngOnInit(): void {
  }

  workers(): void {
    this.router.navigate(['/Workers']);
  }

  logout(){
    this.authService.logout();
  }

}
