import {Injectable} from '@angular/core';
import {Observable, of} from 'rxjs';
import {Food} from '../model/Food';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {catchError} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class FoodService {
  private foodsUrl = 'api/foods';  // URL to web api
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(
    private http: HttpClient) { }

  getFoods(): Observable<Food[]> {
    return this.http.get<Food[]>(this.foodsUrl)
      .pipe(
        catchError(this.handleError<Food[]>('getFoods', []))
      );
  }

  getFoodById(id: string): Observable<Food> {
    return this.http.get<Food>(this.foodsUrl + "/food/" + id)
      .pipe(
        catchError(this.handleError<Food>('getFoodById', undefined))
      );
  }

  createFood(food: Food): Observable<any> {
    return this.http.post<any>(this.foodsUrl, food, this.httpOptions);
  }

  update(food: Food): Observable<Food> {
    return this.http.post<Food>(this.foodsUrl + "/food/update", food, this.httpOptions)
      .pipe(
        catchError(this.handleError<Food>('updateFood'))
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
