import { Routes } from "@angular/router";
import { TablesLayoutComponent } from "./tables-layout/tables-layout.component"

export const TablesRoutes: Routes = [
  {
    path: "layout",
    pathMatch: "full",
    component: TablesLayoutComponent,
    // canActivate: [LoginGuard],
  },
];
