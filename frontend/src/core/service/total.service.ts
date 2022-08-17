import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {Observable, of, pipe} from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import {Total} from "../model/total";
import {Food} from "../model/food";


@Injectable({
  providedIn: 'root'
})
export class TotalService {
  private totalUrl = 'api/total';
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(
    private http: HttpClient
  ) { }

  getTotalsByDate(date: String): Observable<Total[]> {
    return this.http.get<Total[]>(this.totalUrl + "/date/" + date)
      .pipe(
        catchError(this.handleError<Total[]>('getTotalsByDate', []))
      )
  }

  /**
   * Handle Http operation that failed.
   * Let the app continue.
   *
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      // this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

}
