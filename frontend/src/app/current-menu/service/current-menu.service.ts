import { Injectable } from '@angular/core';
import { Main } from 'src/main';
import { HttpClient, HttpErrorResponse} from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class CurrentMenuService {

  private readonly COLD_APPETIZER_URL : string = Main.PATH + "meal/getColdAppetizers";
  private readonly HOT_APPETIZER_URL : string = Main.PATH + "meal/getHotAppetizer";
  private readonly MAIN_MEAL_URL : string = Main.PATH + "meal/getMainCourse";
  private readonly DESSERT_URL : string = Main.PATH + "meal/getDesert";
  private readonly SALATE_URL : string = Main.PATH + "meal/getSalad";
  private readonly APPENDICES_URL : string = Main.PATH + "meal/getAppendices";
  

  private readonly DELETE_MEAL_PRICE : string = Main.PATH + "menu/deleteMealInMenu";
  private readonly CHANGE_MEAL_PRICE : string =Main.PATH + "menu/changeMealPriceInMenu"; 
  private readonly MEAL_NOT_IN_MENU_URL : string = Main.PATH + "menu/getMealPricesNotInMenu";
  private readonly ADD_MEAL_MENU : string = Main.PATH + "menu/addMealToMenu";

  constructor(private http: HttpClient) { }

  public getColdAppetizers(){
    return this.http.get(this.COLD_APPETIZER_URL);
  }


  public getHotAppetizer(){
    return this.http.get(this.HOT_APPETIZER_URL);
  }


  public getMainCourse(){
    return this.http.get(this.MAIN_MEAL_URL);
  }


  public getDesert(){
    return this.http.get(this.DESSERT_URL);
  }


  public getSalad(){
    return this.http.get(this.SALATE_URL);
  }


  public getAppendices(){
    return this.http.get(this.APPENDICES_URL);
  }



  
  public delete(mealPrice:any) {
    return this.http.put<any>(this.DELETE_MEAL_PRICE, mealPrice).pipe(catchError(this.errorHandler));
  }


  public change(mealPrice:any){
    return this.http.put<any>(this.CHANGE_MEAL_PRICE, mealPrice).pipe(catchError(this.errorHandler));
  }


  public getMealsNotInCurrent() {
    return this.http.get(this.MEAL_NOT_IN_MENU_URL);
  }


  public addMeal(mealPrice:any){
    return this.http.post<any>(this.ADD_MEAL_MENU, mealPrice).pipe(catchError(this.errorHandler));
  }

  errorHandler(error:HttpErrorResponse){
    return throwError(error);
  }
}
