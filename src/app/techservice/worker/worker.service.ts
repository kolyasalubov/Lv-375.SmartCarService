import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs/internal/observable/throwError';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class WorkerService {

  allWorkersUrl = '/api/v1/techservices/{id}/workers';//'/api/v1/workers'
  addWorkerToTechnicalService = '/api/v1/techservices/{id}/workers/{username}';

  constructor(private http: HttpClient) { }
/*
  getAllWorkers() {
    return this.http.get<Worker[]>(this.allWorkersUrl)
                    .pipe(catchError(this.errorHandler));
    
  }
*/
  getAllWorkers(techServiceId: number) {
    return this.http.get<Worker[]>(this.allWorkersUrl.replace('{id}', techServiceId.toString()))
                    .pipe(catchError(this.errorHandler));
  }

  registerWorker(username: string, techServiceId: number) {
    return this.http.post(this.addWorkerToTechnicalService
                                .replace('{id}', techServiceId.toString())
                                .replace('{username}', username), httpOptions)
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
