import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Main} from '../../../../main';

@Injectable({
  providedIn: 'root'
})
export class ChefProfileService {

  readonly URL : string = Main.PATH + "chef/get";
  
  constructor(private http: HttpClient) { }

  getChef(){
    return this.http.get(this.URL);
  }
}
