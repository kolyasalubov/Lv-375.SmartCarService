import { Component, OnInit, Input } from '@angular/core';
import { TimeList } from './time-list';
import { TimeListService } from './time-list-service';
import { WorkTime } from './work-time';
import { NewBooking } from './new-booking';
import { error } from '@angular/compiler/src/util';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-time-list',
  templateUrl: './time-list.component.html',
  styleUrls: ['./time-list.component.scss']
})
export class TimeListComponent implements OnInit {

  @Input()
  carId : number;

  @Input()
  requiredTime : number;

  @Input()
  timeList : TimeList;
  error: ErrorEvent;
  workTime : Map<string, Array<WorkTime>> = new Map();
  newBooking : NewBooking;
  postBookingStatusCode : number;
  lastId : string;
  constructor(private timeListServices : TimeListService, private router: Router) {}

  ngOnInit() {
    this.getTimeList()
  }

  getTimeList(){
    this.timeListServices.getBookingTime(this.timeList)
    .subscribe((data) => this.workTime = data,
    error => this.error = error);
  }

  buttonClick(){
    this.getTimeList();
  }

  getHourFromDate(date : string) : string{
    return date.slice(11, 16);
  }

  isEmptyDate(date : Array<WorkTime>): boolean{
   return date.length == 0;
    //return work.length == 0;
  }

  chooseDate(start : string){
    if(this.newBooking == undefined){
      this.newBooking = new NewBooking();
    }
    if(this.newBooking.start == start){
      this.newBooking = new NewBooking();
      return;
    }
    this.newBooking.start = start;
    this.newBooking.requiredTime = this.requiredTime;
    this.newBooking.workerId = this.timeList.workerId;
    this.newBooking.carId = this.carId;
    console.log(this.newBooking);
  }
  postBooking(){
    if(this.newBooking != undefined){
        this.timeListServices.postNewBooking(this.newBooking)
        .subscribe((date) => this.postBookingStatusCode = date,
        error => this.error = error);
  }
    else{
      this.postBookingStatusCode = 404;
    }
}
goToHomePage() {
  this.router.navigate(['/home']);
}
select(startSession : string){
  if(this.lastId != undefined){
    document.getElementById(this.lastId).className = "jumbotron text-center hoverable p-4 ";
  }
  if(this.lastId == startSession){
    document.getElementById(this.lastId).className = "jumbotron text-center hoverable p-4 "
  }
  else{
  document.getElementById(startSession).className = "jumbotron text-center hoverable p-4 " + "select";
  }

  this.lastId = startSession;
}

}
