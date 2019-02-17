import { Component, OnInit, Input } from '@angular/core';

import { ChartService } from './chart.service';
import { ChartDto } from './chart-dto';
import { Tires } from '../../fake-data-btn/tires';

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

  public chartDatasets: Array<any> = [];
  public chartLabels: Array<any> = [];
  public colors: Array<any> = [{ borderWidth: 2, }];

  private date: string;


  constructor(private chartService: ChartService) {
  }

  public setDataAndLabels(chartDto: ChartDto){
    this.getLabel();
    let isNull: boolean = chartDto === null;
    if(this.sensorType == 'tire pressure'){
      this.chartDatasets = [
        { data: isNull ? null : chartDto.data['frontLeft'], label: 'frontLeft' },
        { data: isNull ? null : chartDto.data['frontRight'], label: 'frontRight' },
        { data: isNull ? null : chartDto.data['backLeft'], label: 'backLeft' },
        { data: isNull ? null : chartDto.data['backRight'], label: 'backRight' },
      ];
    } else {
      this.chartDatasets = [
        { data: isNull ? null : chartDto.data, label: this.date },
      ];
    }

    this.chartLabels = isNull ? null : chartDto.labels;
  }

  // public setColors() {
  //   console.log(this.chartDatasets[0].data.length());
  //   for(let i = 0; i < this.chartDatasets[0].data; i++){
  //     this.colors[0].backgroundColor.push('rgba(153, 102, 255, 0.2)');
  //     this.colors[0].borderColor.push('rgba(153, 102, 255, 1)');
  //   }
  // }

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

  private getChartDto(period = this.period): void {
    this.chartService.getChartData(this.sensorType, this.carId, period)
    .subscribe(
      data => {
        this.setDataAndLabels(data)
        // this.setColors();
      },
      error => console.error('Error: ', error)
    );
  }

  changePeriod(period: string): void {
    this.period = period;
    this.getChartDto();
  }

  changeSelection(selection = ''): void {
    this.getChartDto(this.period + selection);
  }

}