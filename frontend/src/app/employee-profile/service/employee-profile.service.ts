import { Injectable } from '@angular/core';
import { Main } from 'src/main';
import { HttpClient, HttpErrorResponse} from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmployeeProfileService {

  readonly URL : string = Main.PATH + "api/users/";

  constructor(private http: HttpClient) { }


  errorHandler(error:HttpErrorResponse){
    return throwError(error);
  }

  updateUser(id, user) {
    return this.http.put<any>(this.URL + id, user).pipe(catchError(this.errorHandler));
  }

}