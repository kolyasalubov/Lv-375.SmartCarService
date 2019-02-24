import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';

import {DateDto} from './date-dto';
import {ChartDto} from './chart-dto';

@Injectable()
export class ChartService {

  // private URL: string = '/chart';
  private URL: string = '/api/chart';

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    }),
    params: {}
  };

  constructor(private http: HttpClient) {
  }

  public getChartData(sensorType: string, carId: number, period: string, date: Date = new Date()) {
    let dateDto: DateDto = new DateDto(sensorType, carId, this.newDateInMySqlString(date));
    return this.getDataFromDB(dateDto, this.URL + period);
  }

  private newDateInMySqlString(date: Date): string {
    let iso: string = date.toISOString();
    return iso.slice(0, 10) + ' ' + iso.slice(11, 19);
  }

  private getDataFromDB(dateDto: DateDto, url: string) {
    this.httpOptions.params = new HttpParams()
      .set("sensorType", dateDto.sensorType)
      .set("carId", String(dateDto.carId))
      .set("date", dateDto.date);

    return this.http.get<ChartDto>(url, this.httpOptions);
  }

}
