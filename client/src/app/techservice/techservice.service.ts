import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Techservice } from './techservice';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';


const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class TechserviceService {

  techserviceUrl = '/api/v1/users/1/techservices';

  constructor(private http: HttpClient) { }

  createTechnicalService(techservice: Techservice) {
    return this.http.post(this.techserviceUrl + '?name=' + techservice.name
                                              + '&address=' + techservice.address
                                              , techservice)
                                              .pipe(catchError(this.errorHandler));
  }
  
  
  errorHandler(error: HttpErrorResponse)  {
    if (error.error instanceof ErrorEvent) {
      console.error('An error occurred:', error.error.message); // A client-side or network error occurred.
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong,
      console.error(
        `Backend returned code ${error.status}, ` +
        `body was: ${error.error}`);
    }
    // return an observable with a user-facing error message
    return throwError(
      'Something bad happened; please try again later.');
  }
}
