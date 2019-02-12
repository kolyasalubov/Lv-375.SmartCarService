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
  map : Map<string, Array<WorkTime>>;
  constructor(private timeListServices : TimeListService) {}

  ngOnInit() {
    this.getTimeList()
  }

  getTimeList(){
    this.timeListServices.getBookingTime(this.timeList)
    .subscribe((data) => this.map = data,
    error => this.error = error);
  }

  buttonClick(){
    this.getTimeList();
  }

}
