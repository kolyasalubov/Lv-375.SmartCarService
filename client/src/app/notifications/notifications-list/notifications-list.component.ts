import { Component, OnInit, Input } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { Observable } from 'rxjs';
import { Notifications } from '../notifications';
import { NotificationsService } from '../notifications.service';

@Component({
  selector: 'app-notifications-list',
  templateUrl: './notifications-list.component.html',
  styleUrls: ['./notifications-list.component.scss']
})
export class NotificationsListComponent implements OnInit {

  notifications: Notifications[];
  private sorted = false;
  // message : string;
  // showModal : boolean;

  @Input() notification: Notifications;
  @Input() id: number;
  constructor(private router: Router,
              private notificationsService: NotificationsService,
              private route: ActivatedRoute) { 
                let stompClient = this.notificationsService.connect();
                stompClient.connect({}, frame => {
                  stompClient.subscribe('/notifications-list', notifications => {
                    console.log("notiffffications: ", notifications);
                    this.notifications.unshift(JSON.parse(notifications.body));
                  })
                })
              }

  ngOnInit() {
    this.route.params.subscribe(params => {this.id = params["id"];});
    this.notificationsService.currentNotifications.subscribe(data => this.notifications = data);
    this.notificationsService.updateNotifications(this.notificationsService.getAllNotifications(this.id));
  }
  deleteNotification(id : number): void {
    this.notificationsService.deleteNotification(id);
  }

  applyFor() : void{
    let isSelected = false;
    let carIds = new Set();
    this.notifications.forEach(
      n => {
        if(n.isSelected) {
          isSelected = true;
          carIds.add(n.carId);
        }
      }
    )
    if(isSelected && carIds.size === 1) {
      this.router.navigate(['/ui/notifications-approvement', this.id]);
    } else {
     
      if (carIds.size > 1){
        // this.message = "You can choose only one car at once";
       
      } else {
        // this.message = "Select notifications^^";
        
      }
      // this.showModal = true;
    }
  }

  sortBy(by: string | any): void {

    this.notifications.sort((a: any, b: any) => {
      if (a[by] < b[by]) {
        return this.sorted ? 1 : -1;
      }
      if (a[by] > b[by]) {
        return this.sorted ? -1 : 1;
      }

      return 0;
    });

    this.sorted = !this.sorted;
  }
}
