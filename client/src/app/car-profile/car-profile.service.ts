import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { OwnerCar } from './owner-car';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class CarProfileService {

  constructor(private http: HttpClient) { }

  createCar(car: OwnerCar){
 this.http.post('http://localhost:9501/ucar/?brand='+ car.brand +'&model='+ car.model +'&graduation_year=' + car.graduation_year + '&number=' + car.number + '&vin=' + car.vin, car);
  }
}
