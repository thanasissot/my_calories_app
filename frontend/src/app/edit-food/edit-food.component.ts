import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from "@angular/forms";
import { FoodService } from "../../core/service/food.service";
import { ActivatedRoute, Router } from "@angular/router";
import { NotificationService } from "../../core/service/notification.service";
import { Food } from "../../core/model/Food";

@Component({
  selector: 'app-edit-food',
  templateUrl: './edit-food.component.html',
  styleUrls: ['./edit-food.component.css']
})
export class EditFoodComponent implements OnInit {
  public food: any;
  private foodId: string | null | undefined;
  loading: boolean = true;
  form: any;

  constructor(private foodService: FoodService,
              private router: Router,
              private route: ActivatedRoute,
              private notifyService: NotificationService) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.foodId = this.route.snapshot.paramMap.get('id');
      this.foodService.getFoodById(this.foodId || '').subscribe(food => {
        this.food = food;
        this.createForm(this.food);
      })
    });
  }

  private createForm(food: Food) {
    this.form = new FormGroup({
      name: new FormControl((food.name), [Validators.required],),
      calories: new FormControl((food.calories), [Validators.required]),
      fat: new FormControl((food.fat), [Validators.required]),
      carb: new FormControl((food.carb), [Validators.required]),
      protein: new FormControl((food.protein), [Validators.required])
    })
    this.form.controls['name'].disable();
    this.loading = false;
  }

  formSubmit(form: FormGroup) {
    if (form.valid) {
      this.form.controls['name'].enable();
      this.food = form.value;
      this.foodService.update(this.food).subscribe(response => {
        this.notifyService.showSuccess("Edit Food completed");
        this.router.navigate(['foods']);
      });
    } else {
      form.markAsDirty();
      this.notifyService.showError("Form is invalid");
    }
  }

}
