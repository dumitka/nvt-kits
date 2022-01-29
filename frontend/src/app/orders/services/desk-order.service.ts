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
  private readonly ORDERED_DRINKS_URL: string = Main.PATH + 'api/orderedDrinks/';
  private readonly ORDERED_MEALS_URL: string = Main.PATH + 'api/orderedMeals/';

  constructor(private http: HttpClient) { }

  errorHandler(error: HttpErrorResponse) {
    return throwError(error);
  }

  getDesk(deskId: number) {
    return this.http.get(this.DESKS_URL + 'desk/' + deskId);
  }

  getOrder(deskId: number) {
    return this.http.get(this.ORDERS_URL + 'deskOrder/' + deskId);
  }

  createOrder(deskId: number) {
    return this.http.get(this.ORDERS_URL + 'createOrder/' + deskId, { responseType: 'text' });
  }

  createOrderedDrink(orderId: number, element: any) {
    return this.http.post(this.ORDERED_DRINKS_URL + 'createOrderedDrink/' + orderId, element, { observe: 'response' });
  }

  createOrderedMeal(orderId: number, element: any) {
    return this.http.post(this.ORDERED_MEALS_URL + 'createOrderedMeal/' + orderId, element, { observe: 'response' });
  }

  updateOrder() {

  }

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
