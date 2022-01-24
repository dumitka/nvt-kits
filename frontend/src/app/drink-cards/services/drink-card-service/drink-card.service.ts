import { Injectable } from '@angular/core';
import { Main } from '../../../../main';
import { HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class DrinkCardService {

  constructor(private http: HttpClient) { }
  
  readonly URL : string = Main.PATH + "api/drinkCards/";

  getDrinks() {
    return this.http.get(this.URL);
  }
  
  addDrink(novoPice){
    return this.http.get(this.URL + "newDrinkCard", {params: {'drinkCardDTO': novoPice}});
  }
}
