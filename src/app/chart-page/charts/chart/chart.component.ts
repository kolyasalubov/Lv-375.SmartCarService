import { Component, OnInit, Input } from '@angular/core';

import { ChartService } from './chart.service';
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

  public chartDatasets: Array<any>;
  public chartLabels: Array<any>;

  private date: string;


  constructor(private chartService: ChartService) {
  }

  public setDataAndLabels(chartDto: ChartDto){
    this.getLabel();
    let isNull: boolean = chartDto === null;
    this.chartDatasets = [
      { data: isNull ? null : chartDto.data, label: this.date },
    ];
    this.chartLabels = isNull ? null : chartDto.labels;
  }

  private getLabel(): void{
    const MONTHS = ["January", "February", "March", "April", "May", "June",
      "July", "August", "September", "October", "November", "December"
    ];

    let date: Date = new Date();
    let day = date.getDate();
    let month = MONTHS[date.getMonth()];
    let year = date.getFullYear();

    if(this.period.includes('day'))
      this.date = `${day}  ${month}, ${year}`
    else if(this.period.includes('month'))
      this.date = `${month}, ${year}`
    else
      this.date = year.toString();
  }

  ngOnInit(): void {
  }

  changePeriod(period: string): void {
    this.period = period;
    this.getChartDto();
  }

  changeSelection(selection = ''): void {
    this.getChartDto(this.period + selection);
  }

  private getChartDto(period = this.period): void {
    this.chartService.getChartData(this.sensorType, this.carId, period)
    .subscribe(
      data => this.setDataAndLabels(data),
      error => console.error('Error: ', error)
    );
  }

}
