import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {EditFoodComponent} from "./edit-food.component";

const routes: Routes = [
  { path: ':id', component: EditFoodComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EditFoodRoutingModule { }
