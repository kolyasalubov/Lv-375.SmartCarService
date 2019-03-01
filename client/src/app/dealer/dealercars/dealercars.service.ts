import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import{Car} from 'src/app/cars/car';
import { Observable } from 'rxjs';

import { from } from 'rxjs';
const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json'
  })
};


@Injectable({
  providedIn: 'root'
})
export class DealercarsService {

  constructor(private http:HttpClient) { }

getAllDealerCars(username:String):Observable<Car[]> {

  return this.http.get<Car[]>('http://localhost:9501/api/dealer/cars/' + username);


}


}