import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TablesLayoutComponent } from './tables-layout/tables-layout.component';
import { MatGridListModule} from '@angular/material/grid-list';
import { HttpClientModule } from '@angular/common/http';

import { AngularMaterialModule } from '../angular_material.module';



@NgModule({
  declarations: [
    TablesLayoutComponent,
  ],
  imports: [
    CommonModule,
    AngularMaterialModule,
    MatGridListModule,
    HttpClientModule
  ],
  exports: [
    TablesLayoutComponent,
  ],
})
export class TablesModule { }
