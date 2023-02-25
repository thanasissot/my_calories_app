import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Food} from "../../../core/model/Food";
import {FoodService} from "../../../core/service/food.service";
import {Meal} from "../../../core/model/Meal";
import {MealService} from "../../../core/service/meal.service";
import {NotificationService} from "../../../core/service/notification.service";

@Component({
  selector: 'app-add-total',
  templateUrl: './add-total.component.html',
  styleUrls: ['./add-total.component.css']
})
export class AddTotalComponent implements OnInit {
  form: any;
  loading: boolean = true;
  total: any;
  foods: Food[] = [];
  @Input()
  date: Date | undefined;

  constructor(
    private foodService: FoodService,
    private totalService: MealService,
    private notifyService: NotificationService,
  ) { }

  ngOnInit(): void {
    this.createForm();
    this.foodService.getFoods().subscribe(foods => {
      this.foods = foods;
      this.loading = false;
    })

  }

  private createForm() {
    this.form = new FormGroup({
      food: new FormControl((''), [Validators.required]),
      gram: new FormControl((''), [Validators.required]),
    })
  }

  formSubmit(form: FormGroup) {
    if (form.valid) {
        this.total = form.value;
        this.total.date = this.date;
        this.total.calories = this.total.food.calories * this.total.gram * 0.01;
        this.total.foodName = this.total.food.name;
        this.totalService.createTotal(this.total).subscribe( {
          complete: () => {
            this.notifyService.showSuccess("Meal Added to your diary");
            window.location.reload();
            },
          next(): void {},
          error: () => {
            this.notifyService.showWarning("Something went wrong");
          }
        });
      } else {
        form.markAsDirty();
        this.notifyService.showError("Form is invalid");
    }
  }

}
