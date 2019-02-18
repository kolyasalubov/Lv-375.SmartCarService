import { Component, OnInit, Input } from '@angular/core';

import { ChartService } from '../chart/chart.service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'progress-chart',
  templateUrl: './progress-chart.component.html',
})
export class ProgressChartComponent implements OnInit {

  @Input()
  sensorType: string;

  @Input()
  carId: number;
  
  chartService: ChartService;

  constructor(private http: HttpClient) {
    this.chartService = new ChartService(http);
  }

  ngOnInit() {
  }

}
