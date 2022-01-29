import { Injectable } from '@angular/core';
import { Main } from 'src/main';
import { HttpClient, HttpErrorResponse} from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DrinkOrderService {

  private readonly NOT_ACCEPTED_ORDERED_DRINKS_URL : string = Main.PATH + "api/orderedDrinks/notAccepted/";
  private readonly ACCEPTED_ORDERED_DRINK_URL : string = Main.PATH + "api/orderedDrinks/acceptDrink";
  private readonly ACCEPTED_ORDERED_DRINKS_URL : string = Main.PATH + "api/orderedDrinks/accepted/";
  private readonly FINISH_ORDERED_DRINK_URL : string = Main.PATH + "api/orderedDrinks/finishDrink";

  constructor(private http: HttpClient) { }


  public notAcceptedOrderedDrinks() {
    return this.http.get(this.NOT_ACCEPTED_ORDERED_DRINKS_URL);
  }


  public acceptOrderedDrink(id:number) {
    return this.http.put<any>(this.ACCEPTED_ORDERED_DRINK_URL, id).pipe(catchError(this.errorHandler));
  }


  public acceptedOrderedDrinks(userId:number) {
    return this.http.get(this.ACCEPTED_ORDERED_DRINKS_URL + userId);
  }

  public finishOrderedDrink(id:number) {
    return this.http.put<any>(this.FINISH_ORDERED_DRINK_URL, id).pipe(catchError(this.errorHandler));
  }

  errorHandler(error:HttpErrorResponse){
    return throwError(error);
  }


}