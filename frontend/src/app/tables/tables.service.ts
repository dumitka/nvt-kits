import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Main } from 'src/main';

@Injectable({
  providedIn: 'root'
})
export class TablesService {

  constructor(private http: HttpClient) { }
  
  readonly URL : string = Main.PATH + "api/desks/";

  //na klik zavrsi


  allDesks() {
    return this.http.get(this.URL);
  }
  
  addNewOrUpdate(table){
    return this.http.post(this.URL , table);
  }

  delete(id) {
    return this.http.delete(this.URL  + id);
  }

  resize(id, size) {
    return this.http.put(this.URL + id, size);
  }

  deleteAll() {
    return this.http.delete(this.URL);
  }
}
