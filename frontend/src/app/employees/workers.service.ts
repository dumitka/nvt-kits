import { Injectable } from '@angular/core';
import { Main } from "../../main";
import { HttpClient, HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class WorkersService {

  constructor(private http: HttpClient) { }
  
  readonly URL : string = Main.PATH + "api/users/";
  
  getWorkers() {
    return this.http.get(this.URL);
  }

  fireUser(id){
    return this.http.delete(this.URL + id).pipe(catchError(this.errorHandler));
  }

  errorHandler(error:HttpErrorResponse){
    return throwError(error);
  }
}
