import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Food} from "../../../core/model/food";
import {FoodService} from "../../../core/service/food.service";
import {Total} from "../../../core/model/total";
import {TotalService} from "../../../core/service/total.service";
import {NotificationService} from "../../../core/service/notification.service";
import {Router} from "@angular/router";

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
  selectedFood: any;
  @Input()
  date: Date | undefined;

  constructor(
    private foodService: FoodService,
    private totalService: TotalService,
    private notifyService: NotificationService,
    private router: Router

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
        console.log(this.total)
        this.totalService.createTotal(this.total).subscribe( {
          complete: () => {
            this.notifyService.showSuccess("Total Added to your diary");
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
