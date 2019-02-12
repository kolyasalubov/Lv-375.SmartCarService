import { Component, OnInit, Input } from '@angular/core';

import { ChartComponent } from '../chart/chart.component';
import { ChartService } from '../chart/chart.service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'doughnut-bar-chart',
  templateUrl: '../chart/chart.component.html',
})
export class DoughnutBarChartComponent extends ChartComponent implements OnInit {

  constructor(private http: HttpClient) {
    super(new ChartService(http));
  }

  ngOnInit() {
  }

  public chartType: string = 'doughnut';

  public chartColors: Array<any> = [
    {
      backgroundColor: ['#F7464A', '#46BFBD', '#FDB45C', '#949FB1', '#4D5360'],
      hoverBackgroundColor: ['#FF5A5E', '#5AD3D1', '#FFC870', '#A8B3C5', '#616774'],
      borderWidth: 2,
    }
  ];

  public chartOptions: any = {
    responsive: true
  };
  
  public chartClicked(e: any): void { }
  public chartHovered(e: any): void { }

}
