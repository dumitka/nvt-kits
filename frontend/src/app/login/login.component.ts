import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from './auth.service';
import { MatSnackBar, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup
  RESPONSE_OK: number = 0;
  RESPONSE_ERROR: number = -1;
  verticalPosition: MatSnackBarVerticalPosition = "top";

  constructor(private fb: FormBuilder, private router: Router, private authService: AuthService, private snackBar: MatSnackBar) { }

  ngOnInit(): void {
    let username = history.state.data === undefined ? '' : history.state.data.username;
    this.loginForm = this.fb.group({
      username: [username, Validators.required],
      password: ['', Validators.required]
    });
    if (username !== '') {
      this.loginForm.controls['username'].disable();
    }
  }

  public hasError = (controlName: string, errorName: string) => {
    return this.loginForm.controls[controlName].hasError(errorName);
  }

  login() {
    this.authService.login(this.loginForm.getRawValue())
      .subscribe(
        data => {
          this.openSnackBar("Uspešno ste ulogovani. Dobrodošli! :)", this.RESPONSE_OK);
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
          } else if (this.authService.getTokenData()?.role === "ROLE_SERVER") {
            this.router.navigate(['/ServerProfile']);
          }
        },
        error => {
          if (error.status === 404) {
            this.openSnackBar("Pogrešno korisničko ime ili lozinka :)", this.RESPONSE_OK);
          }
        });
  }

  openSnackBar(msg: string, responseCode: number) {
    this.snackBar.open(msg, "x", {
      duration: responseCode === this.RESPONSE_OK ? 3000 : 20000,
      verticalPosition: this.verticalPosition,
      panelClass: responseCode === this.RESPONSE_OK ? "back-green" : "back-red"
    });
  }
}
