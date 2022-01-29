import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/employee-profile/model/user.model';
import { WorkersService } from '../workers.service';

import { MatSnackBar, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';

@Component({
  selector: 'app-workers',
  templateUrl: './workers.component.html',
  styleUrls: ['./workers.component.css']
})
export class WorkersComponent implements OnInit {

  RESPONSE_OK : number = 0;
  RESPONSE_ERROR : number = -1;
  verticalPosition: MatSnackBarVerticalPosition = "top";

  displayedColumns: string[] = ['position', 'name', 'roleName', 'salary', 'fire-button', 'button' ];
  listOfWorkers: User[];

  constructor(private service: WorkersService, private router: Router, private snackBar: MatSnackBar) { }


  ngOnInit(): void {
    this.service.getWorkers().subscribe((data: any) =>
    {
      console.log(data);
      this.listOfWorkers = data;
    })
  }


  goBack(){
    this.router.navigate(['/DirectorProfile']);
  }

  register() {
    this.router.navigate(['/Registration']);
  }

  open(person : any) {
    console.log("EVO NAS KLINULOOOJSDKAN", person)
    this.router.navigate(['/EmployeeProfile'], {state:{data:{ "person":person}}});
    // {state:{data:{ "person":person}}}
  }

  fire(id: number) {
    console.log("EVO HOCEMO DA OTPUSTIMO", id);
    this.service.fireUser(id).subscribe((data: any) =>
    {
      console.log(data);
      this.openSnackBar("Uspešno ste otpustili korisnika. :)", this.RESPONSE_OK);
      this.service.getWorkers().subscribe((data: any) =>
      {
        console.log(data);
        this.listOfWorkers = data;
      })
    },
    error => {
      this.openSnackBar("Došlo je do nekakvog problema, molim Vas pokušajte ponovo kasnije! :)", this.RESPONSE_ERROR);
    })
  }

  openSnackBar(msg: string, responseCode: number) {
    this.snackBar.open(msg, "x", {
      duration: responseCode === this.RESPONSE_OK ? 3000 : 20000,
      verticalPosition: this.verticalPosition,
      panelClass: responseCode === this.RESPONSE_OK ? "back-green" : "back-red"
    });
  }

}
