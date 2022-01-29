import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Main} from '../../../../../main';

@Injectable({
  providedIn: 'root'
})
export class BartenderProfileService {

  readonly URL1 : string = Main.PATH + "api/users/";
  
  constructor(private http: HttpClient) { }

  getBartender(){
    return this.http.get(this.URL1 + "info");
  }
}
