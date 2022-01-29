import { Injectable } from '@angular/core';
import { Main } from "../../../../../main";
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class WaiterProfileService {

  constructor(private http: HttpClient) { }
  
  readonly URL : string = Main.PATH + "api/desks/";
  

  getAllDesks(){
    return this.http.get(this.URL);
  }

  setTableNum(id, tableNum) {
    return this.http.put(this.URL + id +"/number/" +tableNum, null)
  }

}
