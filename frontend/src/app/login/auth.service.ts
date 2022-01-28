import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { Main} from '../../main';

@Injectable({
    providedIn: 'root'
  })
  export class AuthService {
  
    constructor
    (
      private router: Router,
      private http: HttpClient
    ) { }
  
    private access_token = null;
    private token_data = null;
  
    login(user: any) {
      const body = {
        'username': user.username,
        'password': user.password
      };
      return this.http.post<any>(Main.PATH + "auth/login", body)
        .pipe(map((res) => {
          this.access_token = res.accessToken;
          this.token_data = res;
          window.localStorage.setItem("token", this.access_token);
        }, catchError(this.errorHander)));
    }
  
    logout() {
      this.access_token = null;
      this.token_data = null;
      window.localStorage.setItem("userId", null);
      window.localStorage.setItem("username", null);
      window.localStorage.setItem("role", null);
      this.router.navigate(['/login']);
    }
  
   
    tokenIsPresent() {
      return this.access_token != null;
    }
  
    getToken() {
      return this.access_token;
    }
  
    getTokenData() {
      if ( this.token_data != null) {
        window.localStorage.setItem("userId", this.token_data.id);
        window.localStorage.setItem("username", this.token_data.username);
        window.localStorage.setItem("role", this.token_data.role);
        return this.token_data;
      } else {
        return {
          id: 0,
          username: "",
          role: "",
        };
      }
    }
  
    errorHander(error: HttpErrorResponse) {
      return throwError(error);
    }
  }