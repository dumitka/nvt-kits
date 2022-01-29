import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';


import { Router } from '@angular/router';
import { User } from 'src/app/employee-profile/model/user.model';
import { NewUser } from '../model/new-user.model';
import { RegistrationService } from '../service/registration.service';
import { MatSnackBar, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  user: NewUser;
  userForm: FormGroup;
  selected: 'ROLE_WAITER'

  RESPONSE_OK : number = 0;
  RESPONSE_ERROR : number = -1;
  verticalPosition: MatSnackBarVerticalPosition = "top";

  constructor( private fb: FormBuilder,
    private router: Router,
    private service: RegistrationService,
    private snackBar: MatSnackBar) { 

      this.createForm();
    }

  ngOnInit(): void {

  }

  createForm() {
    this.userForm = this.fb.group({
      name: ["",[Validators.required, Validators.minLength(2)]],
      lastName: ["", Validators.required],
      username: ["", Validators.required],

      password: ["", Validators.required],

      salary: [0, Validators.required],
      roleName:["",Validators.required]
    })
  }

  onSubmit() {
    this.user = this.userForm.value;
    // this.user.roleName = this.userForm[].value;
    console.log("VREDNOST ROLA" + this.user.roleName)

    this.service.register(this.user).subscribe(response =>
    {
      this.openSnackBar("Uspešno ste registrovali radnika", this.RESPONSE_OK);
      this.router.navigate(["/Workers"])
    }, 
    error => {
      console.log(error)
      this.openSnackBar("Došlo je do nekakve greške, možda je username zauzet, molim Vas pokušajte ponovo! :)", this.RESPONSE_ERROR);
    })
  }





  openSnackBar(msg: string, responseCode: number) {
    this.snackBar.open(msg, "x", {
      duration: responseCode === this.RESPONSE_OK ? 3000 : 20000,
      verticalPosition: this.verticalPosition,
      panelClass: responseCode === this.RESPONSE_OK ? "back-green" : "back-red"
    });
  }

  goBack() {
    this.router.navigate(["/Workers"])
  }

}
