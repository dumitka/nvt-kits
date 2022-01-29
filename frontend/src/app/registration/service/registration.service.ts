import { Injectable } from '@angular/core';
import { Main } from "../../../main";
import { HttpClient, HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  constructor(private http: HttpClient) { }
  
  readonly URL : string = Main.PATH + "api/users/";
  
  register(newUser) {
    return this.http.post<any>(this.URL, newUser).pipe(catchError(this.errorHandler));
  }

  errorHandler(error:HttpErrorResponse){
    return throwError(error);
  }
}
