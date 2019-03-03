import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpResponse} from "@angular/common/http";
import {Dealer} from 'src/app/dealer/dealer';
import {Apply} from './apply';
import {Observable} from "rxjs";
const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class DealersService {

  constructor(private http:HttpClient) { }

  getAllDealers(){
    return this.http.get<Dealer[]>('http://localhost:9501/api/dealer/getAll');
  }

  // createDealer(dealer: Dealer,username: String):Observable<HttpResponse<Dealer>>{
  //
  //   return this.http.post<Dealer>('http://localhost:9501/api/dealer/create/'+username,dealer,{observe: 'response' });
  // }

  applyToDealer(apply:Apply):Observable<HttpResponse<Apply>>{
    console.log(apply.dealerEdr+" , "+apply.stoId);
    return this.http.post<Apply>('http://localhost:9501/api/dealer/epplyToDealer',apply,{observe: 'response' });
  }
}
