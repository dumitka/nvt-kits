import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Main} from '../../../../../main';

@Injectable({
  providedIn: 'root'
})
export class ChefProfileService {

  readonly URL : string = Main.PATH + "chef/get";
  readonly ALL_MEALS_URL : string = Main.PATH + "meal/getAllMeals";
  
  constructor(private http: HttpClient) { }

  getChef(){
    return this.http.get(this.URL);
  }


  getAllMeals(){
    return this.http.get(this.ALL_MEALS_URL);
  }
}
