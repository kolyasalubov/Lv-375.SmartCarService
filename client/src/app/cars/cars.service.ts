import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Car} from './car';
import {Observable} from 'rxjs';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class CarsService {

  constructor(private http: HttpClient) {
  }

  getOwnerCarsById(id: Number): Observable<Car[]> {
    return this.http.get<Car[]>('http://localhost:9501/ownercars/' + id);
  }

  deleteCarById(id: number) {
    return this.http.delete('http://localhost:9501/car/' + id);
  }

  getCarById(id: number): Observable<Car> {
    return this.http.get <Car>('http://localhost:9501/car/' + id);
  }

}
