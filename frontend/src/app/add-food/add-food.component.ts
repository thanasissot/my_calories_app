import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Food} from "../../core/model/food";
import {NotificationService} from "../../core/service/notification.service";
import {FoodService} from "../../core/service/food.service";


@Component({
  selector: 'app-add-food',
  templateUrl: './add-food.component.html',
  styleUrls: ['./add-food.component.css']
})
export class AddFoodComponent implements OnInit {
  public food: any;
  form: any;

  constructor(private foodService: FoodService, private router: Router, private notifyService: NotificationService) { }

  ngOnInit(): void {
    this.createForm();
  }

  addFood(name: string, calories: number, fat: number, carb: number, protein: number): void {
    console.log(calories)
    this.foodService.createFood({name, calories, fat, carb, protein} as Food)
      .subscribe(food => {
        // this.foods.push(food); SHOW MESSAGE FOOD WAS CREATED SUCCESSFULLY
      })
  }

  private createForm() {
    this.form = new FormGroup({
      name: new FormControl((''), [Validators.required]),
      calories: new FormControl((''), [Validators.required]),
      fat: new FormControl((''), [Validators.required]),
      carb: new FormControl((''), [Validators.required]),
      protein: new FormControl((''), [Validators.required])
    })
  }

  formSubmit(form: FormGroup) {
    if (form.valid) {
      this.food = form.value;
      this.foodService.createFood(this.food).subscribe(response => {
        this.notifyService.showSuccess("Save Food completed");
        this.router.navigate(['foods']);
      });
    } else {
      form.markAsDirty();
      this.notifyService.showError("Form is invalid");
    }
  }


}