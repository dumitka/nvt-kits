import { Injectable } from '@angular/core';
import { Main } from '../../../../main';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }
  
  readonly URL : string = Main.PATH + "api/users/info";
  
  getInfo(){
    return this.http.get(this.URL);
  }

}
