import {Component, OnInit, Input, ViewChild} from '@angular/core';
import {WorkerService} from './worker.service';
import {TechserviceComponent} from '../techservice.component'
import {AuthService} from 'src/app/auth/auth.service';
import {SignUpInfo} from 'src/app/auth/signup-info'
import {ROLES} from 'src/app/roles/mock-roles';
import {SkillComponent} from 'src/app/techservice/worker/skill/skill.component'
import {Skill} from './skill/skill';

@Component({
  selector: 'app-worker',
  templateUrl: './worker.component.html',
  styleUrls: ['./worker.component.scss']
})
export class WorkerComponent implements OnInit {

  error: ErrorEvent;
  workers: Worker[] = [];

  @Input() techserviceId: number;
  @ViewChild(SkillComponent) skill: SkillComponent;

  registerFormStub: SignUpInfo = new SignUpInfo('', '', '', '', '', 'WORKER');
  registerForm: SignUpInfo = this.registerFormStub;

  constructor(private workerService: WorkerService, private authService: AuthService) {
  }

  ngOnInit() {
    this.recieveWorkers()
  }

  deleteWorker(workerId: number) {
    this.workerService.deleteWorkerById(workerId).subscribe();
  }

  recieveWorkers() {
    this.workerService.getAllWorkers(this.techserviceId)
      .subscribe(data => this.workers = data,
        error => this.error = error);
  }

  registerWorker() {
    this.authService.signUp(this.registerForm).subscribe();
    setTimeout(() => {
      this.workerService.initialiseWorker(
        this.registerForm.username,
        this.techserviceId,
        this.skill.selectedSkill)
        .subscribe(() => {
          this.recieveWorkers()
        })
    }, 1000); //before was   2000

    this.registerForm = this.registerFormStub; //not works
  }
}
