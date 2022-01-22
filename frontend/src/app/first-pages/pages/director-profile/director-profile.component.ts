import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-director-profile',
  templateUrl: './director-profile.component.html',
  styleUrls: ['./director-profile.component.css']
})
export class DirectorProfileComponent implements OnInit {

  constructor(
    private http: HttpClient
  ) {
    
   }

  ngOnInit(): void {

  }

}
