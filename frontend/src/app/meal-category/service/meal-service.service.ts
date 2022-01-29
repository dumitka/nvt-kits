import { Injectable } from '@angular/core';
import { Main} from '../../../main';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MealServiceService {
  private readonly ADD_MEAL_URL : string = Main.PATH + "meal/addMeal";
  private readonly GET_MEAL_URL : string = Main.PATH + "meal/getOne";
  private readonly CHANGE_MEAL_URL : string = Main.PATH + "meal/changeMeal";
  private readonly DELETE_MEAL_URL : string = Main.PATH + "meal/deleteMeal";
  private readonly GET_ALL_MEALS_URL : string = Main.PATH + "meal/getAllMeals";

  constructor(private http: HttpClient) { 

  }
  
  public addMeal(meal:any) {
    return this.http.post<any>(this.ADD_MEAL_URL, meal).pipe(catchError(this.errorHandler));
  }

  public changeMeal(meal:any){
    return this.http.put<any>(this.CHANGE_MEAL_URL, meal).pipe(catchError(this.errorHandler));
  }


  public deleteMeal(meal:any){
    return this.http.put<any>(this.DELETE_MEAL_URL, meal).pipe(catchError(this.errorHandler));
  }


  public getMeal(id:any){
    return this.http.get(this.GET_MEAL_URL + "/id="+ id);
  }


  public getAllMeals(){
    return this.http.get(this.GET_ALL_MEALS_URL);
  }


  errorHandler(error:HttpErrorResponse){
    return throwError(error);
  }
}
