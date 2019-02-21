import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Techservice } from './techservice';
import { catchError } from 'rxjs/operators';
import { throwError, Observable } from 'rxjs';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class TechserviceService {

  techserviceUrl = '/api/v1/users/{userId}/techservices';
  usersTechserviceUrl = "/api/v1/users/{userId}/techservice";
  crudTechserviceUrl = '/api/v1/techservices/{id}';

  constructor(private http: HttpClient) { }

  createTechnicalService(techservice: Techservice, userId: number) {
    return this.http.post(this.techserviceUrl.replace('{userId}', userId.toString())
                                             + '?name=' + techservice.name
                                              + '&address=' + techservice.address
                                              , techservice)
                                              .pipe(catchError(this.errorHandler));
  }
  
  getTechnicalServiceByCurrentUser(userId: number): Observable<Techservice> {
    return this.http.get<Techservice>(this.usersTechserviceUrl
                                      .replace('{userId}', userId.toString()))
                                      .pipe(catchError(this.errorHandler));
  }

  updateTechnicalService(techservice: Techservice) {
    return this.http.put(this.crudTechserviceUrl.replace('{id}', techservice.stoId.toString())
                                             + '?name=' + techservice.name
                                              + '&address=' + techservice.address
                                              , techservice)
                                              .pipe(catchError(this.errorHandler));
  }

  deleteTechservice(techServiceId: number) {
    return this.http.delete(this.crudTechserviceUrl.replace('{id}', techServiceId.toString()))
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
