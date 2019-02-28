import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient, HttpErrorResponse } from '@angular/common/http';
import { throwError, Observable } from 'rxjs';
import { ServicesFeedbackForm } from '../services-feedback-form/services-feedback-form';
import { catchError } from 'rxjs/operators';
import { ServicesFeedback } from './services-feedback';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class ServicesFeedbackService {

  feedbackByServiceId: string = '/api/techservices/{serviceId}/feedback';
  feedbackByUserName: string = '/api/users/{userName}/feedback';

  constructor(private http: HttpClient) { }
 
  sendFeedback(feedback: ServicesFeedbackForm) {
    return this.http.post(
      this.feedbackByServiceId.replace('{serviceId}', feedback.serviceId.toString()), 
      feedback, 
      httpOptions).pipe(catchError(this.errorHandler));
  }
  
  getFeedbackByServiceId(serviceId: number): Observable<ServicesFeedback[]> {
    return this.http.get<ServicesFeedback[]>(
      this.feedbackByServiceId.replace('{serviceId}', serviceId.toString()))
        .pipe(catchError(this.errorHandler));
  }

  getFeedbackByUserName(userName: string): Observable<ServicesFeedback[]> {
    return this.http.get<ServicesFeedback[]>(
      this.feedbackByServiceId.replace('{userName}', userName))
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
