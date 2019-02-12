import { Component, OnInit, Input } from '@angular/core';

import { ChartService } from './chart.service';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { ChartDto } from './chart-dto';

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

  private period: string = '/day';

  public chartDto: ChartDto = new ChartDto();

  public chartDatasets: Array<any>;
  public chartLabels: Array<any>;


  constructor(private chartService: ChartService) {
    // this.chartDatasets = [{ data: this.chartDto.data, label: this.sensorType },];
    // this.chartLabels = this.chartDto.labels;

    // console.log("NGONINIT");
    // this.getChartDto();
    // this.setDataAndLabels();
  }

  public setDataAndLabels(){
    let isNull: boolean = this.chartDto === null;
    this.chartDatasets = [
      { data: isNull ? null : this.chartDto.data, label: this.sensorType },
    ];
    this.chartLabels = isNull ? null : this.chartDto.labels;

    console.log(this.chartDto);
    console.log(this.chartDatasets);
  }

  ngOnInit(): void {
  }

  changePeriod(period: string): void {
    this.period = period;
    this.getChartDto();
    this.setDataAndLabels();
  }

  changeSelection(selection = ''): void {
    this.getChartDto(this.period + selection);
    this.setDataAndLabels();
  }

  private getChartDto(period = this.period): void {
    this.chartService.getChartData(this.sensorType, this.carId, period)
    .subscribe(
      // data => console.log(data),
      data => {
        this.chartDto = data;
        this.setDataAndLabels();
      },
      error => console.error('Error: ', error)
    );
  }

}
