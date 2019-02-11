import { Component, OnInit, Input } from '@angular/core';

import { ChartService } from './chart.service';

@Component({
  selector: 'chart',
  templateUrl: './chart.component.html',
  providers: [ChartService]
})
export class ChartComponent implements OnInit {

  @Input()
  sensorType: string;

  @Input()
  carId: number;

  private chartService: ChartService;

  private period: string = '/day';

  data: number[];
  labels: string[];

  constructor() { 
    this.chartService = new ChartService();
  }

  ngOnInit(): void {
    console.log("NGONINIT");
    this.getChartData();
  }

  changePeriod(period: string): void {
    this.period = period;
    this.getChartData();
  }

  changeSelection(selection = ''): void {
    this.getChartData(this.period + selection);
  }

  private getChartData(period = this.period): void {
    // console.log(period);
    this.chartService.getChartData(this.sensorType, this.carId, period);
  }

}
