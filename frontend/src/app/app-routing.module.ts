import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { FoodComponent } from "./food/food.component";
import { AddFoodComponent } from "./add-food/add-food.component";
import { MydiaryComponent } from "./mydiary/mydiary.component";
import { EditFoodComponent } from "./edit-food/edit-food.component";

const formatDate = (): string => {
  let d = new Date(),
    month = '' + (d.getMonth() + 1),
    day = '' + d.getDate(),
    year = d.getFullYear();

  if (month.length < 2)
    month = '0' + month;
  if (day.length < 2)
    day = '0' + day;

  return [year, month, day].join('-');
}

const routes: Routes = [
  { path: '', redirectTo: '/', pathMatch: 'full' },
  { path: 'foods', component: FoodComponent },
  { path: 'add-foods', component: AddFoodComponent },
  { path: 'edit-food/:id', component: EditFoodComponent },
  { path: 'mydiary', redirectTo: 'mydiary/' + formatDate() },
  { path: 'mydiary/:date', component: MydiaryComponent }
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

