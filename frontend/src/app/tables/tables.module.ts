import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TableComponent } from './table/table.component';
import { ChairComponent } from './chair/chair.component';
import { TablesLayoutComponent } from './tables-layout/tables-layout.component';



@NgModule({
  declarations: [
    TableComponent,
    ChairComponent,
    TablesLayoutComponent
  ],
  imports: [
    CommonModule
  ]
})
export class TablesModule { }
