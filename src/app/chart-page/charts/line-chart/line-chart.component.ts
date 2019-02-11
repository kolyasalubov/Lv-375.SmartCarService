import { Component, OnInit, Input } from '@angular/core';

import { ChartComponent } from '../chart/chart.component';

@Component({
  selector: 'line-chart',
  templateUrl: '../chart/chart.component.html',
})
export class LineChartComponent extends ChartComponent implements OnInit{

  constructor() {
    super();
  }

  ngOnInit() {
    console.log(this.sensorType);
    console.log(this.chartDatasets[0].label);
    this.setChartDatasets();
    this.setChartLabels();
  }

  private setChartDatasets() {
    this.chartDatasets = [
      { data: [65, 59, 80, 81, 56, 55, 40], label: this.sensorType },
    ]
  }

  private setChartLabels() {
    this.chartLabels = this.chartLabels;
  }

  public chartType: string = 'line';

  public chartDatasets: Array<any> = [{ data: [65, 59, 80, 81, 56, 55, 40], label: this.sensorType },];

  public chartLabels: Array<any> = ['January', 'February', 'March', 'April', 'May', 'June', 'July'];

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
