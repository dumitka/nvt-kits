import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/employee-profile/model/user.model';
import { WorkersService } from '../workers.service';

@Component({
  selector: 'app-workers',
  templateUrl: './workers.component.html',
  styleUrls: ['./workers.component.css']
})
export class WorkersComponent implements OnInit {

  displayedColumns: string[] = ['position', 'name', 'roleName', 'salary', 'button'];
  listOfWorkers: User[];

  constructor(private service: WorkersService, private router: Router) { }


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
    this.router.navigate(['/EmployeeProfile']);
  }
}
