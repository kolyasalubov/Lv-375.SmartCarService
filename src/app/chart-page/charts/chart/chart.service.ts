import { Injectable } from '@angular/core';

import { DateDto } from './date-dto';
import { ChartDto } from './chart-dto';

@Injectable()
export class ChartService {

  private URL: string = '/api/chart';
  private chartDto: ChartDto;

  constructor() { }

  public getChartData(sensorType: string, carId: number, period: string): void {
    let date: string =  this.dateToMySqlString(new Date());
    let dateDto: DateDto = new DateDto(sensorType, carId, date);
    this.getDataFromDB(dateDto, this.URL + period);


    
  }

  private dateToMySqlString(date: Date): string{
    let str: string = date.toISOString();
    return str.slice(0, 10) + ' ' + str.slice(11, 19);
  }

  private getDataFromDB(dateDto: DateDto, url: string): void {

    console.log(JSON.stringify(dateDto));

    fetch(url, {
      method: 'POST',
      headers: new Headers({
        'Content-Type':  'application/json'
      }),
      body: JSON.stringify(dateDto)
    })
    .then(res => res.json())
    .then(data => console.log(JSON.stringify(data)))
    // .then(res => this.chartDto = new ChartDto(res.body. ))
      .catch(error => console.error('Error: ', error));

    //   this.http.post(this.url, this.recordDto, this.httpOptions)
    //   .pipe(
    //     catchError(this.errorHandler)
    //   );

      console.log("get");
  }

}
