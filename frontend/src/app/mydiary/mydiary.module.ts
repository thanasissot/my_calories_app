import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MydiaryRoutingModule } from './mydiary-routing.module';
import { MydiaryComponent } from './mydiary.component';


@NgModule({
  imports: [
    CommonModule,
    MydiaryRoutingModule
  ]
})
export class MydiaryModule { }
