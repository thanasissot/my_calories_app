import {Component, LOCALE_ID, OnInit} from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from "@angular/router";
import { MealService } from "../../core/service/meal.service";
import { Meal } from "../../core/model/Meal";
import {Observable} from "rxjs";
import {NotificationService} from "../../core/service/notification.service";
import {MatDatepickerInputEvent} from "@angular/material/datepicker";

@Component({
  templateUrl: './mydiary.component.html',
  styleUrls: ['./mydiary.component.css'],
})
export class MydiaryComponent implements OnInit {
  public date: any;
  displayedColumns: string[] = ['grams', 'name', 'totalCalories', 'actions'];
  public totalsForDate: Meal[] = [];
  sumOfTotalCalories: any;

  constructor(
    private totalService: MealService,
    private route: ActivatedRoute,
    private notifyService: NotificationService,
    private router: Router
    ) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.date = this.route.snapshot.paramMap.get('date');
    });

    this.getTotalsForDate(this.date).subscribe(
      totals => {
        this.totalsForDate = totals;
        this.sumOfTotalCalories = this.calculateTotalDailyCalories(this.totalsForDate);
      }
    );
  }

  getTotalsForDate(date: string): Observable<Meal[]> {
    return this.totalService.getTotalsByDate(date);
  }

  calculateTotalDailyCalories(totals: Meal[]): number {
    return totals.reduce((a, b) => a + b.calories, 0);;
  }

  deleteTotal(element: Meal) {
    this.totalService.deleteTotal(element).subscribe({
      complete: () => {
        this.notifyService.showSuccess("Meal Entry deleted");
        window.location.reload();
      },
      error: () => {
        this.notifyService.showWarning("Something went wrong");
      }
    })
  }

  addEvent(type: string, event: MatDatepickerInputEvent<Date>) {
    const date: Date | null = event.value;
    if (date !== null) {
      let month = '' + (date.getMonth() + 1);
      let day = '' + date.getDate();
      let year = date.getFullYear();
      if (month.length < 2)
        month = '0' + month;
      if (day.length < 2)
        day = '0' + day;
      this.router.navigate(['/mydiary', [year, month, day].join('-')]).then(r => window.location.reload())
    }
    else {
      this.notifyService.showWarning("Something went wrong");
    }
  }

}
