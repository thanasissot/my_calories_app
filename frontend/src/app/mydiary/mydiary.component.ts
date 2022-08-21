import {Component, LOCALE_ID, OnInit} from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from "@angular/router";
import { TotalService } from "../../core/service/total.service";
import { Total } from "../../core/model/total";
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
  public totalsForDate: Total[] = [];
  sumOfTotalCalories: any;

  constructor(
    private totalService: TotalService,
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

  getTotalsForDate(date: string): Observable<Total[]> {
    return this.totalService.getTotalsByDate(date);
  }

  calculateTotalDailyCalories(totals: Total[]): number {
    let sum = 0;
    totals.forEach(total => {
      sum += (total.gram * total.food.calories * 0.01);
    })
    return sum;
  }

  deleteTotal(element: Total) {
    this.totalService.deleteTotal(element).subscribe({
      complete: () => {
        this.notifyService.showSuccess("Total Entry deleted");
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
