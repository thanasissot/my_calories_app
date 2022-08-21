import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from "@angular/router";
import { TotalService } from "../../core/service/total.service";
import { Total } from "../../core/model/total";
import {Observable} from "rxjs";
import {NotificationService} from "../../core/service/notification.service";

@Component({
  templateUrl: './mydiary.component.html',
  styleUrls: ['./mydiary.component.css']
})
export class MydiaryComponent implements OnInit {
  public date: any;
  displayedColumns: string[] = ['grams', 'name', 'totalCalories', 'actions'];
  public totalsForDate: Total[] = [];
  sumOfTotalCalories: any;

  constructor(
    private totalService: TotalService,
    private route: ActivatedRoute,
    private notifyService: NotificationService
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
}
