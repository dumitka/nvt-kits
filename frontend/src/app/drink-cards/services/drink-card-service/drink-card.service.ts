import { Injectable } from '@angular/core';
import { Main } from '../../../../main';
import { HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class DrinkCardService {

  constructor(private http: HttpClient) { }
  
  readonly URL : string = Main.PATH + "api/drinkCards/";

  trenutnaKartaPica() {
    return this.http.get(this.URL);
  }
  
  dodajKartuPica(novaKartaPica){
    return this.http.get(this.URL + "newDrinkCard", novaKartaPica);
  }
}
