import { Injectable } from '@angular/core';
import { Main } from '../../../../main';
import { HttpClient, HttpErrorResponse, HttpResponse } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class DrinkService {

  constructor(private http: HttpClient) { }
  
  readonly URL : string = Main.PATH + "api/drinks/";
  
  dodajPice(novoPice) {
    return this.http.post(this.URL + "addDrink", novoPice);
  }
  
  izmeniPice(izmenjenoPice){
    return this.http.post(this.URL + "editDrink", izmenjenoPice);
  }
  
  izbrisiPice(piceZaBrisanje){
    return this.http.post(this.URL + "deleteDrink", piceZaBrisanje);
  }

  pretraziPica(unetiTekst: string) {
    return this.http.get(this.URL + "searchDrinks/" + unetiTekst);
  }
  
  filtrirajPica(strPretraga: string, strFilter: string) {
    return this.http.get(this.URL + "filterDrinks/" + strPretraga + "/" + strFilter);
  }
  
  svaPica() {
    return this.http.get(this.URL);
  }
}
