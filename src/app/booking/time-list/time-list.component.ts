import { Component, OnInit, Input } from '@angular/core';
import { TimeList } from './time-list';
import { TimeListService } from './time-list-service';
import { WorkTime } from './work-time';

@Component({
  selector: 'app-time-list',
  templateUrl: './time-list.component.html',
  styleUrls: ['./time-list.component.scss']
})
export class TimeListComponent implements OnInit {

  @Input()
  timeList : TimeList;
  error: ErrorEvent;
  workTime : Map<string, Array<WorkTime>> = new Map();
  constructor(private timeListServices : TimeListService) {}

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

}
