import { Injectable } from '@angular/core';
import { Main } from '../../../../main';
import { HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class DrinkService {

  constructor(private http: HttpClient) { }
  
  readonly URL : string = Main.PATH + "api/drinks/";
  
  addDrink(novoPice){
    return this.http.get(this.URL + "addDrink", {params: {'dto': novoPice}});
  }
  
  editDrink(izmenjenoPice){
    return this.http.get(this.URL + "editDrink",  {params: {'dto': izmenjenoPice}});
  }
  
  deleteDrink(piceZaBrisanje){
    return this.http.get(this.URL + "deleteDrink",  {params: {'dto': piceZaBrisanje}});
  }

  searchDrinks(unetiTekst: string) {
    return this.http.get(this.URL + "searchDrinks/" + unetiTekst);
  }
  
  filterDrinks(strPretraga: string, strFilter: string) {
    return this.http.get(this.URL + "filterDrinks/" + strPretraga + "/" + strFilter);
  }
  
  getDrinks() {
    return this.http.get(this.URL);
  }
}
