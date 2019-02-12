import { Component, OnInit, Input } from '@angular/core';

import { ChartComponent } from '../chart/chart.component';
import { ChartService } from '../chart/chart.service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'line-chart',
  templateUrl: '../chart/chart.component.html',
})
export class LineChartComponent extends ChartComponent implements OnInit{

  constructor(private http: HttpClient) {
    super(new ChartService(http));
  }

  ngOnInit() {
    this.changeSelection();
  }

  public chartType: string = 'line';

  public chartColors: Array<any> = [
    {
      backgroundColor: 'rgba(105, 0, 132, .2)',
      borderColor: 'rgba(200, 99, 132, .7)',
      borderWidth: 2,
    },
    {
      backgroundColor: 'rgba(0, 137, 132, .2)',
      borderColor: 'rgba(0, 10, 130, .7)',
      borderWidth: 2,
    }
  ];

  public chartOptions: any = {
    responsive: true
  };
  public chartClicked(e: any): void { }
  public chartHovered(e: any): void { }
}
