import {Component, Input, OnInit} from '@angular/core';
import {ReportDto} from "./report-dto";

@Component({
  selector: 'report',
  templateUrl: './report.component.html',
  styleUrls: ['./report.component.scss']
})
export class ReportComponent implements OnInit {

  @Input()
  report: ReportDto;

  constructor() {
  }

  ngOnInit() {
  }

  // public formatDate(date: Date): string {
  //   let MONTHS = ["January", "February", "March", "April", "May", "June", "July",
  //     "August", "September", "October", "November", "December"];
  //   return `${date.getDay()} ${MONTHS[date.getMonth() - 1]} ${date.getFullYear()} ${date.getHours()}:${date.getMinutes()}`
  // }

  public formatDate(startDate: Date, endDate): string {
    return startDate.toLocaleDateString() === endDate.toLocaleDateString() ? startDate.toLocaleDateString()
      : startDate.toLocaleDateString() + " - " + endDate.toLocaleDateString();
  }

}
