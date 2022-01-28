import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Main} from '../../../../../main';

@Injectable({
  providedIn: 'root'
})
export class AdminProfileService {

  readonly URL : string = Main.PATH + "api/users/";
  
  constructor(private http: HttpClient) { }

  getAdmin(){
    return this.http.get(this.URL + "info");
  }
}
