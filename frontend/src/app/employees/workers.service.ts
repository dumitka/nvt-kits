import { Injectable } from '@angular/core';
import { Main } from "../../main";
import { HttpClient, HttpErrorResponse, HttpResponse } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class WorkersService {

  constructor(private http: HttpClient) { }
  
  readonly URL : string = Main.PATH + "api/users/";
  
  getWorkers() {
    return this.http.get(this.URL);
  }

}
