import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from "@angular/forms";
import { FoodService } from "../../core/service/food.service";
import { Router } from "@angular/router";
import { NotificationService } from "../../core/service/notification.service";

@Component({
  selector: 'app-edit-food',
  templateUrl: './edit-food.component.html',
  styleUrls: ['./edit-food.component.css']
})
export class EditFoodComponent implements OnInit {
  public food: any;
  form: any;

  constructor(private foodService: FoodService, private router: Router, private notifyService: NotificationService) { }

  ngOnInit(): void {
    this.createForm();
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
