import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of, pipe} from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { Meal } from "../model/Meal";


@Injectable({
  providedIn: 'root'
})
export class MealService {
  private totalUrl = 'api/meal';
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(
    private http: HttpClient
  ) { }

  getTotalsByDate(date: String): Observable<Meal[]> {
    return this.http.get<Meal[]>(this.totalUrl + "/date/" + date)
      .pipe(
        catchError(this.handleError<Meal[]>('getTotalsByDate', []))
      )
  }

  createTotal(total: Meal): Observable<any> {
    return this.http.post<any>(this.totalUrl, total, this.httpOptions)
      .pipe(
        catchError(this.handleError<any>('createTotal', null))
      );
  }

  deleteTotal(total: Meal): Observable<any> {
    return this.http.post<any>(this.totalUrl + "/delete", total, this.httpOptions)
      .pipe(
        catchError(this.handleError<any>('deleteTotal', null))
      );
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
