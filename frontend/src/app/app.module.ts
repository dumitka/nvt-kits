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
import { MenuCategoryComponent } from './menu-category/menu-category.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AngularMaterialModule } from './angular_material.module';
import { MealCategoriesComponent } from './meal-category/categories/component/meal-categories.component';
import { MealsOfCategoryComponent } from './meal-category/meals-of-category/component/meals-of-category.component';
import { ServerFirstPageComponent } from './first-pages/pages/server-first-page/server-first-page.component';
import { AddDrinkComponent } from './drinks/pages/add-drink/add-drink.component';
import { AllDrinksComponent } from './drinks/pages/all-drinks/all-drinks.component';
import { DrinkPageComponent } from './drinks/pages/drink-page/drink-page.component';
import { DrinksTableComponent } from './drinks/components/drinks-table/drinks-table.component';
import { DrinkCardPageComponent } from './drink-cards/pages/drink-card-page/drink-card-page.component';
import { MealProfileComponent } from './meal-category/pages/meal-profile/meal-profile.component';


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
    MenuCategoryComponent,
    ServerProfileComponent,
    MealCategoriesComponent,
    MealsOfCategoryComponent,
    ServerFirstPageComponent,
    AddDrinkComponent,
    AllDrinksComponent,
    DrinkPageComponent,
    DrinksTableComponent,
    DrinkCardPageComponent,
    MealProfileComponent,
    
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
        path: 'DrinksTable',
        canActivate: [ServerRoutes],
        component: DrinksTableComponent,
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