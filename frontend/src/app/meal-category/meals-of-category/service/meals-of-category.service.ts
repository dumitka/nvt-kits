import { Injectable } from '@angular/core';
import { Main} from '../../../../../src/main';
import { HttpClient } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class MealsOfCategoryService {
  private readonly COLD_APPETIZER_URL : string = Main.PATH + "meal/getColdAppetizerMeals";
  private readonly HOT_APPETIZER_URL : string = Main.PATH + "meal/getHotAppetizerMeals";
  private readonly MAIN_MEAL_URL : string = Main.PATH + "meal/getMainCourseMeals";
  private readonly DESSERT_URL : string = Main.PATH + "meal/getDesertMeals";
  private readonly SALATE_URL : string = Main.PATH + "meal/getSaladMeals";
  private readonly APPENDICES_URL : string = Main.PATH + "meal/getAppendicesMeals";

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
}
