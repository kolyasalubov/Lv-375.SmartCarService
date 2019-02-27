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

  @Input() notification: Notifications;
  @Input() id: number;
  constructor(private router: Router,
              private notificationsService: NotificationsService,
              private route: ActivatedRoute ) { }

  ngOnInit() {
    this.route.params.subscribe(params => {this.id = params["id"];});
    this.notificationsService.currentNotifications.subscribe(data => this.notifications = data);
    this.notificationsService.updateNotifications(this.notificationsService.getAllNotifications(this.id));
    console.log(this.notifications);
  }
  deleteNotification(id : number): void {
    this.notificationsService.deleteNotification(id)
      .subscribe(
        data => {
          console.log('delete notification data',data);
          this.notifications = this.notifications.filter(n => n.id !== id);
        },
        error => console.log(error));
  }

  applyFor() : void{
    let isSelected = false;
    this.notifications.forEach(
      n => {
        if(n.isSelected) {
          isSelected = true;
        }
      }
    )
    if(isSelected) {
      this.router.navigate(['/notifications-approvement', this.id]);
    } else {
      alert("Select notifications^^");
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
