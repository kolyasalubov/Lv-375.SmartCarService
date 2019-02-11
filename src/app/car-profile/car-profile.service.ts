import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { OwnerCar } from './owner-car';
import { Observable } from 'rxjs';

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

  createCar(carProfile: OwnerCar, username: String): Observable<OwnerCar>{
   return this.http.put<OwnerCar>('http://localhost:9501/ucar/?brand='+ carProfile.brand +'&model='+ carProfile.model +'&graduationyear=' + carProfile.graduationyear + '&number=' + carProfile.number + '&vin=' + carProfile.vin + '&username=' + username, carProfile, httpOptions);
 }
}
