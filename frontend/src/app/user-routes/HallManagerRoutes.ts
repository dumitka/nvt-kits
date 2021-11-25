import { Injectable } from "@angular/core";
import { CanActivate } from "@angular/router";
import { AuthService } from "../login/auth.service";

@Injectable({
    providedIn: 'root'
  })
  export class HallManagerRoutes implements CanActivate {
  
    constructor(private authService: AuthService) { }
  
    canActivate() {
      if (this.authService.getTokenData()?.role === 'ROLE_HALL_MANAGER') {
        return true;
      }
      return false;
    }
}