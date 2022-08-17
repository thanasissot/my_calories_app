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
  public totalsForDate: Total[]  | undefined;

  constructor(private totalService: TotalService,
              private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.date = this.route.snapshot.paramMap.get('date');
    });

    this.getTotalsForDate(this.date).subscribe(
      totals => this.totalsForDate = totals
    );
  }

  getTotalsForDate(date: string): Observable<Total[]> {
    return this.totalService.getTotalsByDate(date);

  }






}
