import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable, BehaviorSubject } from 'rxjs';
import { Notifications } from  './notifications';
 
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class NotificationsService {

  private baseUrl = '/api/notifications';
  private notificationsSource = new BehaviorSubject<Notifications[]>([]);
  currentNotifications = this.notificationsSource.asObservable();

  private msgSource = new BehaviorSubject<String>("Default msg source");
  currentMsg = this.msgSource.asObservable();

  updateNotifications(notifications : Observable<Notifications[]>){
    notifications.subscribe(data => this.notificationsSource.next(data));
  }

  updateMsg(msg: String) {
    this.msgSource.next(msg);
  }

  constructor(private http: HttpClient) { }

  public getAllNotifications(id) {
    return this.http.get<Notifications[]>(this.baseUrl + "/" + id);
  }

  public deleteNotification(id){
    return this.http.delete(this.baseUrl + "/" + id);
  }

}
