import { Component, OnInit, Input } from '@angular/core';
import { ServicesFeedbackForm } from './services-feedback-form';
import { Techservice } from '../techservice/techservice';
import { UsersService } from '../users/users.service';
import { TokenStorageService } from '../auth/token-storage.service';
import { Worker } from '../techservice/worker/worker';
import { TechserviceService } from '../techservice/techservice.service';
import { ServicesFeedbackService } from '../services-feedback/services-feedback.service';

@Component({
  selector: 'services-feedback-form',
  templateUrl: './services-feedback-form.component.html',
  styleUrls: ['./services-feedback-form.component.scss']
})
export class ServicesFeedbackFormComponent implements OnInit {

  feedbackForm: ServicesFeedbackForm = new ServicesFeedbackForm();
  techservice: Techservice = new Techservice();
  //@Input() workers: Worker[]; 
  /*@Input()*/ workers: number[] = [10, 11, 12]; 

  constructor(private tokenStorage: TokenStorageService,
    private techserviceService: TechserviceService,
    private servicesFeedbackService: ServicesFeedbackService) { 

  }

  ngOnInit() {
    this.getTechnicalServiceFromWorker(this.workers[0]);
    this.feedbackForm.userName = this.tokenStorage.getUsername();
  }

  getTechnicalServiceFromWorker(workerId: number) {
    this.techserviceService.getTechnicalServiceByCurrentUser(workerId)
      .subscribe(data => {
        this.techservice = data;
        this.feedbackForm.serviceId = this.techservice.stoId;
      });
  }

  sendFeedback() {
    console.log(this.feedbackForm);
    this.servicesFeedbackService.sendFeedback(this.feedbackForm).subscribe();
  }
}
