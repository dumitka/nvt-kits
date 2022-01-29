import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpParams } from '@angular/common/http';
import { Main } from '../../../main';
import { Order } from 'src/app/models/order';
import { catchError, Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DeskOrderService {
  private readonly DESKS_URL: string = Main.PATH + 'api/desks/';
  private readonly ORDERS_URL: string = Main.PATH + 'api/orders/';

  constructor(private http: HttpClient) { }

  errorHandler(error: HttpErrorResponse) {
    return throwError(error);
  }

  getDesk() {
    return this.http.get(this.DESKS_URL + 'desk/' + 3); // TODO: dodati deskId
  }

  getOrder() {
    return this.http.get(this.ORDERS_URL + 'deskOrder/' + 3); // TODO: dodati deskId
  }

  // createOrder
  // updateOrder

  deleteOrder(orderId: number) {
    return this.http.put(this.ORDERS_URL + 'deleteOrder/' + orderId, null, { responseType: 'text' }).pipe(catchError(this.errorHandler));
  }

  serveDrinks(deskId: number) {
    return this.http.put(this.ORDERS_URL + 'serveDrinks/' + deskId, null, { responseType: 'text' }).pipe(catchError(this.errorHandler));
  }
  
  serveMeals(deskId: number) {
    return this.http.put(this.ORDERS_URL + 'serveMeals/' + deskId, null, { responseType: 'text' }).pipe(catchError(this.errorHandler));
  }

  chargeOrder(orderId: number) {
    return this.http.get(this.ORDERS_URL + 'chargeOrder/' + orderId, { responseType: 'text' }).pipe(catchError(this.errorHandler));
  }
}
