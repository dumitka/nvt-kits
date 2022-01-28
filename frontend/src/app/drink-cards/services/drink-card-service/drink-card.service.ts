import { Injectable } from '@angular/core';
import { Main } from '../../../../main';
import { HttpClient} from '@angular/common/http';
import { DrinkCardDTO } from 'src/app/models/drinkCardDTO';
import { DrinkPriceDTO } from 'src/app/models/drinkPriceDTO';

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
    console.log(novaKartaPica);
    return this.http.post(this.URL + "newDrinkCard", novaKartaPica);
  }
}
