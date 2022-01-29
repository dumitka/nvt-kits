import { Injectable } from '@angular/core';
import { Main } from "../../../../../main";
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class WaiterProfileService {
  readonly WAITERS_URL: string = Main.PATH + "api/users/waiters";
  readonly DESKS_URL: string = Main.PATH + "api/desks/";

  constructor(private http: HttpClient) { }

  getWaiters() {
    return this.http.get(this.WAITERS_URL);
  }

  getDesks() {
    return this.http.get(this.DESKS_URL);
  }
}
