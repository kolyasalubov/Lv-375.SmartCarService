import { Component, OnInit } from '@angular/core';
@Component({
  selector: 'app-worker-list',
  templateUrl: './worker-list.component.html',
  styleUrls: ['./worker-list.component.scss'],
})
export class WorkerListComponent implements OnInit {

  map = new Map([['a', [1,2,3]], ['b', [4,5,6]]]);

  constructor() {}

  ngOnInit() {
  }

  getWorkerList(){
    
  }

  

}
