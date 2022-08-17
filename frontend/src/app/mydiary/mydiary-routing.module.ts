import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {MydiaryComponent} from "./mydiary.component";



const routes: Routes = [
  { path: '', component: MydiaryComponent, pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MydiaryRoutingModule {}
