import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Main } from '../../../main';
import { Order } from 'src/app/models/order';

@Injectable({
  providedIn: 'root'
})
export class DeskOrderService {
  readonly URL : string = Main.PATH + 'api/';

  constructor(private http : HttpClient) { }

  getDesk() {
    return this.http.get(this.URL + 'desks/desk/1'); // TODO: id deska
  }

  getOrder() {
    return this.http.get(this.URL + 'orders/deskOrder/1'); // TODO: id deska
  }

  createOrder(order : Order) {
    return this.http.post(this.URL + 'createOrder/1', order); // TODO: id deska
  }
}
