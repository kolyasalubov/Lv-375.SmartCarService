import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';

import { DateDto } from './date-dto';
import { ChartDto } from './chart-dto';

@Injectable()
export class ChartService {

  private URL: string = '/api/chart';

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json'
    }),
    params: {}
  };

  constructor(private http: HttpClient) { }

  public getChartData(sensorType: string, carId: number, period: string) {
    let date: string =  this.dateToMySqlString(new Date());
    let dateDto: DateDto = new DateDto(sensorType, carId, date);
    return this.getDataFromDB(dateDto, this.URL + period);
  }

  private getDataFromDB(dateDto: DateDto, url: string) {
    // let chartDto: ChartDto;

    this.httpOptions.params = new HttpParams()
      .set("sensorType", dateDto.sensorType)
      .set("carId", String(dateDto.carId))
      .set("date", dateDto.date);

    return this.http.get<ChartDto>(url, this.httpOptions);
    // .subscribe(
    //   data => chartDto = data,
    //   error => console.error('Error: ', error)
    // );

    // console.log(chartDto);
    // return chartDto;
  }

  private dateToMySqlString(date: Date): string{
    return date.toISOString().slice(0, 10) + ' ' + date.toLocaleTimeString();
  }

}
