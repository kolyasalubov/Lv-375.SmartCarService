import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { from, Observable } from 'rxjs';
import { DealerSto } from './dealersto';
import{Techservice} from 'src/app/techservice/techservice';
const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class DealerstoaddService {

  constructor(private http:HttpClient) { }

  // createDealerSto(username:String,sto:DealerSto):Observable<DealerSto>{

  //   return this.http.put<DealerSto>('http://localhost:9501/api/dealer/createtechservices/?username=' + username + '&name='+sto.name + '&address='+ sto.address,sto);

  // }
  //  createDealerSto(username:String,sto:Techservice):Observable<Techservice>{
  //   return this.http.put<Techservice>('http://localhost:9501/api/dealer/createtechservices/?username=' + username + '&name='+sto.name + '&address='+ sto.address,sto);
  // }

  // getAllDealersSto(username:String):Observable<Techservice[]>{
  //   return this.http.get<Techservice[]>('http://localhost:9501/api/dealer/allstos/'+username);
  // }

  getAllStosApply(username:String):Observable<Techservice[]>{
      return this.http.get<Techservice[]>('http://localhost:9501/api/dealer/allstosToApply/'+username);

  }
}
