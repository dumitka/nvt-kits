import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ChefProfileService {

  readonly URL : string = "http://localhost:8080/chef/get";
  
  constructor(private http: HttpClient) { }

  getChef(){
    return this.http.get(this.URL);
  }
}
