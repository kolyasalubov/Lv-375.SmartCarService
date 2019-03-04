import { Component, OnInit, Input } from '@angular/core';
import { WorkerListService } from './worker-list-service';
import { Worker } from './worker';
import { WorkerList } from './worker-list-real';
import { TimeList } from '../time-list/time-list';
import { WorkersTime } from './workers-time';

@Component({
  selector: 'app-worker-list',
  templateUrl: './worker-list.component.html',
  styleUrls: ['./worker-list.component.scss'],
})
export class WorkerListComponent implements OnInit {

  showTime : boolean = false;
  showWorker: boolean = true;

  @Input()
  carId : number;

  @Input()
  workerList : WorkerList;

  @Input()
  cost : number;

  @Input()
  userId : number;

  @Input()
  fromNoti : boolean;

  workersTime : WorkersTime;
  timeList : TimeList = new TimeList();
  workerForm : any = {};
  map : Map<string, Array<Worker>>;
  lastIndexMap : Map<string, number> = new Map();
  error: ErrorEvent;

  getDefault : boolean = false;
  defaulWorker : Map<string, number> = new Map();
  timeError : boolean = false;
  date : string;
  constructor(private workerListService : WorkerListService) {}

  ngOnInit() {
    this.getWorkerList();
  }

  getWorkerList(){
    this.workerListService.getWorkerByCar(this.workerList)
    .subscribe((data) => this.workersTime = data,
    error => this.error = error);
  }

  ChooseWorker(worker : Worker, skillname : string) :void{
    this.showTime = false;
      if(this.lastIndexMap.get(skillname) != undefined){
        if(this.lastIndexMap.get(skillname) == -1){
          this.setClass(worker.workerId, 'select');
          this.lastIndexMap.set(skillname, worker.workerId);
          return;
        }
        if(worker.workerId == this.lastIndexMap.get(skillname)){
          this.setClass(worker.workerId, 'non-select');
          this.lastIndexMap.set(skillname, -1);
        }
        else{
          this.setClass(this.lastIndexMap.get(skillname), 'non-select');
          this.setClass(worker.workerId, 'select');
          this.lastIndexMap.set(skillname, worker.workerId);
        }
      }
      else{
        this.lastIndexMap.set(skillname, worker.workerId);
        this.setClass(worker.workerId, 'select');
      }
  }

  private setClass(id: number, className: string): void{
    document.getElementById('worker'+String(id)).className = "jumbotron text-center hoverable p-4 " + className;
  }

  getFreeTime(){
    this.timeList.time = this.workerForm.time;
    this.timeList.carId = this.carId;
    if(!this.dateValidation(this.timeList.time)){
      this.timeError = true;
      return;
    }
    else{
      this.timeError = false;
    }
    
    let workId : Array<number> = new Array();

    this.defaulWorker.forEach((value: number, key: string) => {
      if(this.lastIndexMap.get(key) != undefined && this.lastIndexMap.get(key) != -1){
        workId.push(this.lastIndexMap.get(key));
      }
      else{
        workId.push(this.defaulWorker.get(key));
      }
    });

    this.timeList.workersId = workId;
    this.timeList.needTime = this.workersTime.requiredTime;

    this.showWorker = false;
    this.showTime = true;
    
  }

  getRequiredTime(requiredTime : number):string{
    let hours = 0;
    while(requiredTime >= 60){
      hours++;
      requiredTime-=60;
    }
    if(hours!=0){
      return (String)(hours) + " hour" + " " + (String)(requiredTime) + " minutes";
    }
    else{
      return (String)(requiredTime) + "minutes"
    }
  }

  addToDefault(skillName : string, worker : Worker): string{
      if(this.defaulWorker.get(skillName) == undefined){
        this.defaulWorker.set(skillName, worker.workerId);
        return worker.fullName;
      }

      if(Math.random() > 0.5){
        this.defaulWorker.set(skillName, worker.workerId);
      }

      return worker.fullName;
  }

  dateValidation(time : string) : boolean{
    if(this.workerForm.time == undefined){
      return false;
    }
    let today = new Date();
    let year : number = (Number)(time.slice(0,4));
    let month : number = (Number)(time.slice(5,7));
    let day : number = (Number)(time.slice(8,10));
    console.log(year);
    console.log(month);
    console.log(day);
    console.log(today.getFullYear());
    console.log(today.getMonth() + 1);
    console.log(today.getDate());
    if(today.getFullYear() > year || (today.getMonth() + 1) > month || today.getDate() > day){
      return false;
    }
    return true;
  }





}
