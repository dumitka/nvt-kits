import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Main} from '../../../../main';

@Injectable({
  providedIn: 'root'
})
export class CookProfileService {

  readonly URL : string = Main.PATH + "cook/get";
  
  constructor(private http: HttpClient) { }

  getCook(){
    return this.http.get(this.URL);
  }
}
