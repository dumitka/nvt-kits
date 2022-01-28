import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TableComponent } from './table/table.component';
import { ChairComponent } from './chair/chair.component';
import { TablesLayoutComponent } from './tables-layout/tables-layout.component';
import {MatGridListModule} from '@angular/material/grid-list';
import { HttpClientModule } from '@angular/common/http';

import { AngularMaterialModule } from '../angular_material.module';



@NgModule({
  declarations: [
    TableComponent,
    ChairComponent,
    TablesLayoutComponent
  ],
  imports: [
    CommonModule,
    AngularMaterialModule,
    MatGridListModule,
    HttpClientModule
  ]
})
export class TablesModule { }
