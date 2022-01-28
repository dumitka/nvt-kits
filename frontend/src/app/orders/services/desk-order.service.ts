import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpParams } from '@angular/common/http';
import { Main } from '../../../main';
import { Order } from 'src/app/models/order';
import { catchError, Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DeskOrderService {
  readonly URL: string = Main.PATH + 'api/';

  constructor(private http: HttpClient) { }

  errorHandler(error: HttpErrorResponse) {
    return throwError(error);
  }

  getDesk() {
    return this.http.get(this.URL + 'desks/desk/1'); // TODO: id deska
  }

  getOrder() {
    return this.http.get(this.URL + 'orders/deskOrder/1'); // TODO: id deska
  }

  // createOrder
  // updateOrder

  deleteOrder(orderId: number) {
    return this.http.put(this.URL + 'orders/deleteOrder/' + orderId, null, { responseType: 'text' }).pipe(catchError(this.errorHandler))
  }

  // deliverDrinks
  // deliverMeals
  // charge
}
