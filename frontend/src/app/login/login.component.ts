import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule, FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from './auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
   loginForm:FormGroup

  constructor
  (
    private fb: FormBuilder, 
    private router: Router,
    private authService: AuthService,
  ) { }

  ngOnInit(): void {
    this.loginForm = this.fb.group(
      {
        username: ['', Validators.required],
        password: ['', Validators.required],
      }
    );
  }


  public hasError = (controlName: string, errorName: string) =>{
    return this.loginForm.controls[controlName].hasError(errorName);
  }

  login() {
    this.authService.login(this.loginForm.value)
      .subscribe(
        data => {
          if (this.authService.getTokenData()?.role === "ROLE_ADMIN") {
            this.router.navigate(['/AdminProfile']);
          } else if (this.authService.getTokenData()?.role === "ROLE_DIRECTOR") {
            this.router.navigate(['/DirectorProfile']);
          } else if (this.authService.getTokenData()?.role === "ROLE_WAITER") {
            this.router.navigate(['/WaiterProfile']);
          } else if (this.authService.getTokenData()?.role === "ROLE_COOK") {
            this.router.navigate(['/CookProfile']);
          } else if (this.authService.getTokenData()?.role === "ROLE_MANAGER") {
            this.router.navigate(['/ManagerProfile']);
          } else if (this.authService.getTokenData()?.role === "ROLE_BARTENDER") {
            this.router.navigate(['/BartenderProfile']);
          } else if (this.authService.getTokenData()?.role === "ROLE_CHEF") {
            this.router.navigate(['/ChefProfile']);
          } else if (this.authService.getTokenData()?.role === "ROLE_HALL_MANAGER") {
            this.router.navigate(['/HallManagerProfile']);
          }
        },
        error => {
          if (error.status === 404) {
            console.log("Invalid username/password!");
          } else if (error.status === 403) {
            console.log("Email has not been verifed!");
          }
        });
    }

   



}
