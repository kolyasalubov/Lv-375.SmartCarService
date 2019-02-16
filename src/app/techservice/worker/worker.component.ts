import { Component, OnInit, Input } from '@angular/core';
import { WorkerService } from './worker.service';
import { TechserviceComponent } from '../techservice.component'
import { AuthService } from 'src/app/auth/auth.service';
import { SignUpInfo } from 'src/app/auth/signup-info'
import { ROLES } from 'src/app/roles/mock-roles';

@Component({
  selector: 'app-worker',
  templateUrl: './worker.component.html',
  styleUrls: ['./worker.component.scss']
})
export class WorkerComponent implements OnInit {

  error: ErrorEvent;

  workers: Worker[];

  @Input() techserviceId: number;

  registerFormStub: SignUpInfo = new SignUpInfo('', '', '', '', '', 'WORKER');
  registerForm: SignUpInfo = this.registerFormStub;

  constructor(private workerService: WorkerService, private authService: AuthService) { }

  ngOnInit() {
    this.recieveWorkers()
  } 

  recieveWorkers() {
    this.workerService.getAllWorkers(this.techserviceId)
          .subscribe(data => this.workers = data,
                    error => this.error = error);
  }

  registerWorker() {
    //this.registerForm.role.push('WORKER');
    this.authService.signUp(this.registerForm).subscribe();
    setTimeout(() => {
      this.workerService.registerWorker(this.registerForm.username, this.techserviceId)
      .subscribe(() => {
        this.recieveWorkers()}
      )}, 2000);
      this.registerForm = this.registerFormStub; //not works
  }
}
