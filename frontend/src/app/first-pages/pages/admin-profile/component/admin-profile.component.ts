import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/login/auth.service';
import { AdminProfileService } from '../service/admin-profile.service';

@Component({
  selector: 'app-admin-profile',
  templateUrl: './admin-profile.component.html',
  styleUrls: ['./admin-profile.component.css']
})
export class AdminProfileComponent implements OnInit {

  loggedAdminName: string;
  loggedAdminLastName: string;

  constructor(
    private service: AdminProfileService, 
    private authService: AuthService,
    private router: Router) {

    this.service.getAdmin().subscribe((data: any) => {
      this.loggedAdminName = data.name;
      this.loggedAdminLastName = data.lastName;

      console.log(this.loggedAdminName);
      console.log(this.loggedAdminLastName);
    })


    }

  ngOnInit(): void {
  }

  modifyLayout(): void {
    this.router.navigate(['/ModifyLayout']);
  }

  logout(){
    this.authService.logout();
  }

}
