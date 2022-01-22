import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CookProfileService {

  readonly URL : string = "http://localhost:8080/cook/get";
  
  constructor(private http: HttpClient) { }

  getCook(){
    return this.http.get(this.URL);
  }
}
