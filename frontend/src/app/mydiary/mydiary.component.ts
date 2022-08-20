import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from "@angular/router";
import { TotalService } from "../../core/service/total.service";
import { Total } from "../../core/model/total";
import {Observable} from "rxjs";

@Component({
  templateUrl: './mydiary.component.html',
  styleUrls: ['./mydiary.component.css']
})
export class MydiaryComponent implements OnInit {
  public date: any;
  displayedColumns: string[] = ['grams', 'name', 'totalCalories'];
  public totalsForDate: Total[] = [];
  sumOfTotalCalories: any;

  constructor(private totalService: TotalService,
              private route: ActivatedRoute) { }

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

}
