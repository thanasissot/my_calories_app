import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms'; // <-- NgModel lives here
import { Food } from '../../core/model/food';
import { FOODS } from "../../core/mock-data/mock-food";
import {FoodService} from "../../core/service/food.service";

@Component({
  selector: 'app-food',
  templateUrl: './food.component.html',
  styleUrls: ['./food.component.css']
})
export class FoodComponent implements OnInit {
  public foods: Food[] = [];

  constructor(private foodService: FoodService) { }

  ngOnInit(): void {
    this.getFoods();
  }

  getFoods(): void {
    this.foodService.getFoods()
      .subscribe(foods => this.foods = foods);
  }

  addFood(name: string, calories: number, fat: number, carb: number, protein: number): void {
    console.log(calories)
    this.foodService.createFood({name, calories, fat, carb, protein} as Food)
      .subscribe(food => {
        this.foods.push(food);
      })
  }

}
