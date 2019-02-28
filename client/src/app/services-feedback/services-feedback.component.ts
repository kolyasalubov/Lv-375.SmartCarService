import { Component, OnInit, Input } from '@angular/core';
import { ServicesFeedback } from './services-feedback';
import { ServicesFeedbackService } from './services-feedback.service';

@Component({
  selector: 'services-feedback',
  templateUrl: './services-feedback.component.html',
  styleUrls: ['./services-feedback.component.scss']
})
export class ServicesFeedbackComponent implements OnInit {

  allFeedback: ServicesFeedback[] = [];
  @Input() serviceId: number = null;
  @Input() userName: string = null;

  constructor(private servicesFeedbackService: ServicesFeedbackService) { }

  ngOnInit() {
    console.log('serviceId: ' + this.serviceId);
    console.log('userName: ' + this.userName);
    if(this.serviceId) {
      this.servicesFeedbackService.getFeedbackByServiceId(this.serviceId).subscribe(data => this.allFeedback = data);
    } else if (this.userName) {
      this.servicesFeedbackService.getFeedbackByUserName(this.userName).subscribe(data => this.allFeedback = data);
    }
  }

}
