import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {TradeIn} from './tradeIIn';
const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json'
  })
};
@Injectable({
  providedIn: 'root'
})
export class TradesinService {

  constructor(private http:HttpClient) { }

  getAllTradeInByDealer(username:String):Observable<TradeIn[]>{
return this.http.get<TradeIn[]>('http://localhost:9501/api/dealer/getAllTradeIn/'+username);
  }
  deleteTradeIn(id: number){
    return this.http.delete('api/delaer/delete/' + id)
  }

  successTradeIn(id: number){
    return this.http.delete('api/delaer/successTradeIn/' + id)
  }
}
