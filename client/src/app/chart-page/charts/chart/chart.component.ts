import {Component, OnInit, Input} from '@angular/core';

import {ChartService} from './chart.service';
import {ChartDto} from './chart-dto';
import {ChartData} from './chart-data';

import COLORS from '../../colors';

@Component({
  selector: 'chart',
  templateUrl: './chart.component.html',
  styleUrls: ['./chart.component.scss'],
  providers: [ChartService]
})
export class ChartComponent implements OnInit {

  @Input()
  sensorType: string;

  @Input()
  carId: number;

  private MONTHS = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
  private PERIODS = ['Day', 'Month', 'Year'];
  private SELECTIONS = ['Avg', 'Min', 'Max'];

  public chartDatasets: Array<any> = [{data: null, label: null}];
  public chartLabels: Array<any> = [null];
  public colors: Array<any> = [{borderWidth: 2,}];

  private period: string = '/day';
  private selection: string = '';
  private date: Date = new Date();


  constructor(private chartService: ChartService) {
  }

  ngOnInit(): void {
  }

  ngAfterViewInit(): void {
    this.setBackgroundColor(`Day_${this.sensorType}`, COLORS.get('LIGHT_BTN'));
  }

  // *****
  // CSS
  // *****

  private changeBtnColors(ids: string[], id: string) {
    for (let i in ids) {
      if (id.indexOf(ids[i]) === -1) {
        this.setBackgroundColor(`${ids[i]}_${this.sensorType}`, COLORS.get('DARK_BTN'));
      } else {
        this.setBackgroundColor(id, COLORS.get('LIGHT_BTN'));
      }
    }
  }

  private setBackgroundColor(id: string, color: string): void {
    const el = document.getElementById(id);
    if (el !== null) {
      el.style.backgroundColor = color;
    }
  }

  private getId(period: string): string {
    return `${period.charAt(1).toUpperCase()}${period.substr(2)}_${this.sensorType}`;
  }

  // *****
  // MAIN METHODS
  // *****

  initCharts(): void {
    this.getChartDto(this.period + this.selection);
  }

  changePeriod(period: string): void {
    this.period = period;
    this.selection = '';
    this.date = new Date();
    this.getChartDto();

    this.changeBtnColors(this.PERIODS, this.getId(period));
    this.changeBtnColors(this.SELECTIONS, `Avg_${this.sensorType}`);
  }

  changeSelection(selection = ''): void {
    this.selection = selection;
    this.getChartDto(this.period + selection);

    this.changeBtnColors(this.SELECTIONS, (selection === '') ? `Avg_${this.sensorType}` : this.getId(selection));
  }

  changeDate(isNext: boolean): void {
    this.setDate(isNext);
    this.getChartDto(this.period + this.selection);
  }

  private setDate(isNext: boolean): void {
    if (this.period.includes('day')) {
      this.date.setDate(this.incrDecrDate(isNext, this.date.getDate()));
    } else if (this.period.includes('month')) {
      this.date.setMonth(this.incrDecrDate(isNext, this.date.getMonth()));
    } else {
      this.date.setFullYear(this.incrDecrDate(isNext, this.date.getFullYear()));
    }
  }

  private incrDecrDate(isNext: boolean, current: number): number {
    return isNext ? (current + 1) : (current - 1);
  }

  private getChartDto(period = this.period): void {
    this.chartService.getChartData(this.sensorType, this.carId, period, this.date)
      .subscribe(
        data => {
          this.setDataAndLabels(data)
          // this.setColors();
        },
        error => console.error('Error: ', error)
      );
  }

  public setDataAndLabels(chartDto: ChartDto) {
    let chartData: ChartData = new ChartData();

    if (this.sensorType == 'tire pressure') {
      const TIRES: string[] = ['frontLeft', 'frontRight', 'backLeft', 'backRight'];
      this.chartDatasets = [];

      TIRES.forEach((tire, i) => {
        chartData.setChartData(chartDto, i);
        this.chartDatasets.push({data: chartData.data, label: tire});
      });
    } else {
      chartData.setChartData(chartDto);
      this.chartDatasets = [{data: chartData.data, label: this.getCurrentPeriod(this.date)}];
    }

    this.chartLabels = (chartDto === null) ? null : this.changeLabels(chartData.labels);
  }

  private changeLabels(oldLabels: string[]): string[] {
    let labels: string[] = [];

    if (this.period.includes('day')) {
      oldLabels.forEach(label => {
        labels.push(label.slice(0, 5));
      });
    } else if (this.period.includes('month')) {
      labels = oldLabels;
    } else {
      oldLabels.forEach(label => {
        labels.push(this.MONTHS[Number(label) - 1]);
      });
    }

    return labels;
  }

  // public setColors() {
  //   console.log(this.chartDatasets[0].data.length());
  //   for(let i = 0; i < this.chartDatasets[0].data; i++){
  //     this.colors[0].backgroundColor.push('rgba(153, 102, 255, 0.2)');
  //     this.colors[0].borderColor.push('rgba(153, 102, 255, 1)');
  //   }
  // }

  private getCurrentPeriod(date: Date): string {
    let day = date.getDate();
    let month = this.MONTHS[date.getMonth()];
    let year = date.getFullYear();

    if (this.period.includes('day')) {
      return `${day} ${month}, ${year}`
    } else if (this.period.includes('month')) {
      return `${month}, ${year}`
    } else {
      return year.toString();
    }
  }

}
