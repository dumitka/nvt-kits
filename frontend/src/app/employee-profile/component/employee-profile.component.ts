import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';


import { Router } from '@angular/router';
import { User } from 'src/app/employee-profile/model/user.model';
import { NewUser } from 'src/app/registration/model/new-user.model';
import { MatSnackBar, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import { EmployeeProfileService } from '../service/employee-profile.service';

@Component({
  selector: 'app-employee-profile',
  templateUrl: './employee-profile.component.html',
  styleUrls: ['./employee-profile.component.css']
})
export class EmployeeProfileComponent implements OnInit {
  user: any;
  userForm: FormGroup;
  // selected: 'ROLE_WAITER'

  RESPONSE_OK : number = 0;
  RESPONSE_ERROR : number = -1;
  verticalPosition: MatSnackBarVerticalPosition = "top";

  currentlyEditing: boolean = false;


  constructor( private fb: FormBuilder,
    private router: Router,
    private service: EmployeeProfileService,
    private snackBar: MatSnackBar) { 

    this.createForm();

    console.log("HEJ AELLIDAJSD")
    //fetch user
    this.user = history.state.data.person;
    //form set value 
    this.userForm.controls['name'].setValue(this.user.name);
    this.userForm.controls['lastName'].setValue(this.user.lastName);
    this.userForm.controls['username'].setValue(this.user.username);
    this.userForm.controls['salary'].setValue(this.user.salary);
    this.userForm.controls['roleName'].setValue(this.user.roleName);
    this.userForm.controls['password'].setValue("");

    this.userForm.disable();
    //fetch
    }

  ngOnInit(): void {

  }

  gotovaIzmena() {
    this.userForm.disable();

    let temp = this.userForm.value;
    temp.roleName = "ROLE_" + this.user.roleName;

    this.service.updateUser(this.user.id, temp).subscribe((data:any) => {
      console.log(data)
      this.openSnackBar("Uspešno ste izmenili podatke. :)", this.RESPONSE_OK);
      this.router.navigate(['/Workers']);
    },
    error => {
      this.openSnackBar("Došlo je do nekakvog problema, molim Vas pokušajte ponovo kasnije! :)", this.RESPONSE_ERROR);
    })

  }

  izmena() {
    this.userForm.enable();
    
  }


  // private String username;
  //   private String name;
  //   private String lastName;
  //   private String password;
  //   private String roleName;
  //   private float salary;

  createForm() {
    this.userForm = this.fb.group({
      name: ["",[Validators.required, Validators.minLength(2)]],
      lastName: ["", Validators.required],
      username: ["", Validators.required],

      salary: [0, Validators.required],
      roleName:["",Validators.required],
      password: ["", Validators.required]
    })
  }

  goBack() {
    this.router.navigate(['/Workers']);
  }

  openSnackBar(msg: string, responseCode: number) {
    this.snackBar.open(msg, "x", {
      duration: responseCode === this.RESPONSE_OK ? 3000 : 20000,
      verticalPosition: this.verticalPosition,
      panelClass: responseCode === this.RESPONSE_OK ? "back-green" : "back-red"
    });
  }
}
