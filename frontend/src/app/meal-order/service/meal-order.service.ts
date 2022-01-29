import { Injectable } from '@angular/core';
import { Main } from 'src/main';
import { HttpClient, HttpErrorResponse} from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MealOrderService {

  private readonly NOT_ACCEPTED_ORDERED_MEALS_URL : string = Main.PATH + "api/orderedMeals/notAccepted";
  private readonly ACCEPTED_ORDERED_MEAL_URL : string = Main.PATH + "api/orderedMeals/acceptMeal";
  private readonly ACCEPTED_ORDERED_MEALS_URL : string = Main.PATH + "api/orderedMeals/accepted/";
  private readonly FINISH_ORDERED_MEALS_URL : string = Main.PATH + "api/orderedMeals/finishMeal";

  constructor(private http: HttpClient) { }


  public notAcceptedOrderedMeals() {
    return this.http.get(this.NOT_ACCEPTED_ORDERED_MEALS_URL);
  }


  public acceptOrderedMeal(id:number) {
    return this.http.put<any>(this.ACCEPTED_ORDERED_MEAL_URL, id).pipe(catchError(this.errorHandler));
  }


  public acceptedOrderedMeals(userId:number) {
    return this.http.get(this.ACCEPTED_ORDERED_MEALS_URL + userId);
  }

  public finishOrderedMeal(id:number) {
    return this.http.put<any>(this.FINISH_ORDERED_MEALS_URL, id).pipe(catchError(this.errorHandler));
  }

  errorHandler(error:HttpErrorResponse){
    return throwError(error);
  }

}
