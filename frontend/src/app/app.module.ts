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
import { AdminProfileComponent } from './admin-profile/admin-profile.component';
import { WaiterProfileComponent } from './waiter-profile/waiter-profile.component';
import { CookProfileComponent } from './cook-profile/cook-profile.component';
import { ManagerProfileComponent } from './manager-profile/manager-profile.component';
import { BartenderProfileComponent } from './bartender-profile/bartender-profile.component';
import { ChefProfileComponent } from './chef-profile/chef-profile.component';
import { HallManagerProfileComponent } from './hall-manager-profile/hall-manager-profile.component';
import { DirectorProfileComponent } from './director-profile/director-profile.component';
import { AuthService } from './login/auth.service';

import { AdminRoutes } from './user-routes/AdminRoutes';
import { BartenderRoutes } from './user-routes/BartenderRoutes';
import { ChefRoutes } from './user-routes/ChefRoutes';
import { CookRoutes } from './user-routes/CookRoutes';
import { HallManagerRoutes } from './user-routes/HallManagerRoutes';
import { ManagerRoutes } from './user-routes/ManagerRoutes';
import { WaiterRoutes } from './user-routes/WaiterRoutes';
import { DirectorRoutes } from './user-routes/DirectorRoutes';


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
    HallManagerProfileComponent,
    DirectorProfileComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,

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
        path: 'HallManagerProfile',
        canActivate: [HallManagerRoutes],
        component: HallManagerProfileComponent,
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
      }
      
    ]),
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
