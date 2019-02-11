import { Component, OnInit } from '@angular/core';
import { WorkerService } from './worker.service';

@Component({
  selector: 'app-worker',
  templateUrl: './worker.component.html',
  styleUrls: ['./worker.component.scss']
})
export class WorkerComponent implements OnInit {

  error: ErrorEvent;

  workers: Worker[];

  constructor(private workerService: WorkerService) { }

  ngOnInit() {
    this.recieveWorkers()
  }

  recieveWorkers() {
    this.workerService.getAllWorkers()
          .subscribe(data => this.workers = data,
                    error => this.error = error);
  }
}
