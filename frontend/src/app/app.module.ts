import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { TokenInterceptor } from './login/TokenInterceptor';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { AdminProfileComponent } from './first-pages/pages/admin-profile/admin-profile.component';
import { WaiterProfileComponent } from './first-pages/pages/waiter-profile/waiter-profile.component';
import { CookProfileComponent } from './first-pages/pages/cook-profile/component/cook-profile.component';
import { ManagerProfileComponent } from './first-pages/pages/manager-profile/manager-profile.component';
import { BartenderProfileComponent } from './first-pages/pages/bartender-profile/bartender-profile.component';
import { ChefProfileComponent } from './first-pages/pages/chef-profile/component/chef-profile.component';
import { DirectorProfileComponent } from './first-pages/pages/director-profile/director-profile.component';
import { ServerProfileComponent } from './first-pages/pages/server-profile/server-profile.component';
import { AuthService } from './login/auth.service';

import { AdminRoutes } from './user-routes/AdminRoutes';
import { BartenderRoutes } from './user-routes/BartenderRoutes';
import { ChefRoutes } from './user-routes/ChefRoutes';
import { CookRoutes } from './user-routes/CookRoutes';
import { ServerRoutes } from './user-routes/ServerRoutes';
import { ManagerRoutes } from './user-routes/ManagerRoutes';
import { WaiterRoutes } from './user-routes/WaiterRoutes';
import { DirectorRoutes } from './user-routes/DirectorRoutes';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AngularMaterialModule } from './angular_material.module';
import { MealCategoriesComponent } from './meal-category/categories/component/meal-categories.component';
import { MealsOfCategoryComponent } from './meal-category/meals-of-category/component/meals-of-category.component';
import { ServerFirstPageComponent } from './first-pages/pages/server-first-page/server-first-page.component';
import { AddDrinkComponent } from './drinks/pages/add-drink/add-drink.component';
import { AllDrinksComponent } from './drinks/pages/all-drinks/all-drinks.component';
import { DrinkPageComponent } from './drinks/pages/drink-page/drink-page.component';
import { DrinkCardPageComponent } from './drink-cards/pages/drink-card-page/drink-card-page.component';
import { MealProfileComponent } from './meal-category/pages/meal-profile/meal-profile.component';
import { NewMealComponent } from './meal-category/pages/new-meal/new-meal.component';
import { ChangeMealComponent } from './meal-category/pages/change-meal/change-meal.component';
import { DeleteMealDialogComponent } from './meal-category/pages/delete-meal/delete-meal-dialog.component';
import { ChoosingDrinksComponent } from './drinks/pages/choosing-drinks/choosing-drinks.component';
import { DrinkViewComponent } from './drinks/pages/drink-view/drink-view.component';
import { DialogDeleteComponent } from './drinks/components/dialog-delete/dialog-delete.component';
import { DialogInputPriceComponent } from './drinks/components/dialog-input-price/dialog-input-price.component';
import { CurrentMenuCategoriesComponent } from './current-menu/pages/current-menu-categories/current-menu-categories.component';
import { CurrentMenuAddMealComponent } from './current-menu/pages/current-menu-add-meal/current-menu-add-meal.component';
import { CurrentMenuMealProfileComponent } from './current-menu/pages/current-menu-meal-profile/current-menu-meal-profile.component';
import { CurrentMenuMealsComponent } from './current-menu/pages/current-menu-meals/current-menu-meals.component';
import { ChangeMealPriceDialogComponent } from './current-menu/component/change-meal-price-dialog/change-meal-price-dialog.component';
import { DeleteDialogComponent } from './drink-cards/components/delete-dialog/delete-dialog.component';
import { PriceDialogComponent } from './drink-cards/components/price-dialog/price-dialog.component';
import { NewMenuCategoriesComponent } from './current-menu/pages/new-menu-categories/new-menu-categories.component';
import { NewMenuMealsComponent } from './current-menu/pages/new-menu-meals/new-menu-meals.component';
import { NewMenuReviewComponent } from './current-menu/pages/new-menu-review/new-menu-review.component';
import { AreYouSureDialogComponent } from './current-menu/component/are-you-sure-dialog/are-you-sure-dialog.component';
import { SaveMenuDialogComponent } from './current-menu/component/save-menu-dialog/save-menu-dialog.component';
import { NewMealOrdersComponent } from './meal-order/pages/new-meal-orders/new-meal-orders.component';
import { TakenMealOrdersComponent } from './meal-order/pages/taken-meal-orders/taken-meal-orders.component';
import { DeskOrderComponent } from './orders/pages/desk-order/desk-order.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    AdminProfileComponent,
    WaiterProfileComponent,
    CookProfileComponent,
    ManagerProfileComponent,
    BartenderProfileComponent,
    ChefProfileComponent,
    DirectorProfileComponent,
    ServerProfileComponent,
    MealCategoriesComponent,
    MealsOfCategoryComponent,
    ServerFirstPageComponent,
    AddDrinkComponent,
    AllDrinksComponent,
    DrinkPageComponent,
    DrinkCardPageComponent,
    MealProfileComponent,
    NewMealComponent,
    ChangeMealComponent,
    DeleteMealDialogComponent,
    ChoosingDrinksComponent,
    DrinkViewComponent,
    DialogDeleteComponent,
    DialogInputPriceComponent,
    CurrentMenuCategoriesComponent,
    CurrentMenuAddMealComponent,
    CurrentMenuMealProfileComponent,
    CurrentMenuMealsComponent,
    ChangeMealPriceDialogComponent,
    DeleteDialogComponent,
    PriceDialogComponent,
    NewMenuCategoriesComponent,
    NewMenuMealsComponent,
    NewMenuReviewComponent,
    AreYouSureDialogComponent,
    SaveMenuDialogComponent,
    NewMealOrdersComponent,
    TakenMealOrdersComponent,
    DeskOrderComponent,
  ],
  entryComponents: [
    DeleteMealDialogComponent,
    ChangeMealPriceDialogComponent,
    AreYouSureDialogComponent,
    SaveMenuDialogComponent
  ],

  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    AngularMaterialModule,


    RouterModule.forRoot([
      { path: '', redirectTo: 'login', pathMatch: 'full' },
      { path: 'login', component: LoginComponent },
      {
        path: 'AdminProfile',
        canActivate: [AdminRoutes],
        component: AdminProfileComponent,
      },
      {
        path: 'BartenderProfile',
        canActivate: [BartenderRoutes],
        component: BartenderProfileComponent,
      },
      {
        path: 'ChefProfile',
        canActivate: [ChefRoutes],
        component: ChefProfileComponent,
      },
      {
        path: 'CookProfile',
        canActivate: [CookRoutes],
        component: CookProfileComponent,
      },
      {
        path: 'DirectorProfile',
        canActivate: [DirectorRoutes],
        component: DirectorProfileComponent,
      },
      {
        path: 'ServerProfile',
        canActivate: [ServerRoutes],
        component: ServerProfileComponent,
      },
      {
        path: 'ServerFirstPage',
        canActivate: [ServerRoutes],
        component: ServerFirstPageComponent,
      },
      {
        path: 'ManagerProfile',
        canActivate: [ManagerRoutes],
        component: ManagerProfileComponent,
      },
      {
        path: 'WaiterProfile',
        canActivate: [WaiterRoutes],
        component: WaiterProfileComponent,
      },
      {
        path: 'WaiterProfileServer',
        canActivate: [ServerRoutes],
        component: WaiterProfileComponent,
      },
      {
        path: 'MealCategories',
        canActivate: [ChefRoutes],
        component: MealCategoriesComponent,
      },
      {
        path: 'MealsOfCategory',
        canActivate: [ChefRoutes],
        component: MealsOfCategoryComponent,
      },
      {
        path: 'AddDrink',
        canActivate: [ServerRoutes],
        component: AddDrinkComponent,
      },
      {
        path: 'AllDrinks',
        canActivate: [ServerRoutes],
        component: AllDrinksComponent,
      },
      {
        path: 'Drink',
        canActivate: [ServerRoutes],
        component: DrinkPageComponent,
      },
      {
        path: 'DrinkView',
        canActivate: [ServerRoutes],
        component: DrinkViewComponent,
      },
      {
        path: 'ChooseDrinks',
        canActivate: [ServerRoutes],
        component: ChoosingDrinksComponent,
      },
      {
        path: 'DrinkCard',
        canActivate: [ServerRoutes],
        component: DrinkCardPageComponent,
      },
      {
        path: 'MealProfile',
        canActivate: [ChefRoutes],
        component: MealProfileComponent,
      },
      {
        path: 'NewMeal',
        canActivate: [ChefRoutes],
        component: NewMealComponent,
      },
      {
        path: 'ChangeMeal',
        canActivate: [ChefRoutes],
        component: ChangeMealComponent,
      },
      {
        path: 'CurrentMenuCategories',
        canActivate: [ChefRoutes],
        component: CurrentMenuCategoriesComponent,
      },
      {
        path: 'CurrentMenuAddMeal',
        canActivate: [ChefRoutes],
        component: CurrentMenuAddMealComponent,
      },
      {
        path: 'CurrentMenuMealProfile',
        canActivate: [ChefRoutes],
        component: CurrentMenuMealProfileComponent,
      },
      {
        path: 'CurrentMenuMeals',
        canActivate: [ChefRoutes],
        component: CurrentMenuMealsComponent,
      },
      {
        path: 'NewMenuCategories',
        canActivate: [ChefRoutes],
        component: NewMenuCategoriesComponent,
      },
      {
        path: 'NewMenuMeals',
        canActivate: [ChefRoutes],
        component: NewMenuMealsComponent,
      },
      {
        path: 'NewMenuReview',
        canActivate: [ChefRoutes],
        component: NewMenuReviewComponent,
      },
      {
        path: 'NewMealOrders',
        canActivate: [ChefRoutes],
        component: NewMealOrdersComponent,
      },
      {
        path: 'TakenMealOrders',
        canActivate: [ChefRoutes],
        component: TakenMealOrdersComponent,
      },
      {
        path: 'DeskOrder',
        canActivate: [WaiterRoutes],
        component: DeskOrderComponent,
      },

    ]),

    BrowserAnimationsModule,
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: TokenInterceptor,
    multi: true,
  },

    AuthService,],
  bootstrap: [AppComponent]
})
export class AppModule { }