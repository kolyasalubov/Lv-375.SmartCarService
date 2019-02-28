import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, from } from 'rxjs';
import{ Dealer} from './dealer';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class DealerService {

  constructor(private http:HttpClient) { }

createDealer(dealer: Dealer,username: String):Observable<Dealer>{

  return this.http.put<Dealer>('http://localhost:9501/api/dealer/create/?dealerName=' + dealer.dealerName+'&dealerAddress='+dealer.dealerAddress+'&dealerEdr='+dealer.dealerEdr+'&dealerEmail='+dealer.dealerEmail+'&username='+ username ,dealer,httpOptions);
}


getDealer(username: String){

  return this.http.get<Dealer>('http://localhost:9501/api/dealer/get/'+username);

}
}
