import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MydiaryRoutingModule } from './mydiary-routing.module';
import { MydiaryComponent } from './mydiary.component';
import { AddTotalComponent } from './add-total/add-total.component';
import {MatExpansionModule} from "@angular/material/expansion";
import {MatCardModule} from "@angular/material/card";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatSelectModule} from "@angular/material/select";


@NgModule({
  imports: [
    CommonModule,
    MydiaryRoutingModule,
    MatExpansionModule,
    MatCardModule,
    MatFormFieldModule,
    MatSelectModule
  ],
  exports: [

  ],
  declarations: [

  ]
})
export class MydiaryModule { }
