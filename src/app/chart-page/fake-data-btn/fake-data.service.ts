import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';

import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';

import { RecordDto } from './record-dto';
import { SENSORS } from './sensors';
import { Sensor } from './sensor';
import { Tires } from './tires';

@Injectable()
export class FakeDataService {
  
  private TIRE: string = 'tire pressure';
  private SENSOR_URL: string = '/api/record';
  private TIRE_URL: string = '/api/record/tire';

  private recordDto: RecordDto = new RecordDto();
  private url: string;

  constructor(private http: HttpClient) {
  }

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json'
    })
  };

  public addFakeData(carVin: string): void {
    const RECORD_QUANTITY: number = 50;

    for(let index in SENSORS){
      let sensor: Sensor = SENSORS[index];
      for (let i = 0; i < RECORD_QUANTITY; i++) {
        this.setRandomDto(carVin, sensor.type);
        this.sendToDB();
      }
    }
  }

  private sendToDB(): void {

    // fetch(this.url, {
    //   method: 'POST',
    //   headers: new Headers({
    //     'Content-Type':  'application/json'
    //   }),
    //   body: JSON.stringify(this.recordDto)
    // }).then(res => console.log('SAVE Record status: ', res.status))
    //   .catch(error => console.error('Error: ', error));

      this.http.post<RecordDto>(this.url, this.recordDto, this.httpOptions)
      .subscribe(
        () => console.log("SAVE OK")
      );

      console.log("add");
  }

  private errorHandler(error: HttpErrorResponse)  {
    if (error.error instanceof ErrorEvent) {
      console.error('An error occurred:', error.error.message); // A client-side or network error occurred.
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong,
      console.error(
        `Backend returned code ${error.status}, ` +
        `body was: ${error.error}`);
    }
    // return an observable with a user-facing error message
    return throwError(
      'Something bad happened; please try again later.');
  }

  private setRandomDto(carVin: string, sensorType: string): void {
    this.setGeneralRecordDto(carVin, sensorType);
    let sensor: Sensor = this.getSensorByType(this.recordDto.sensorType);

    if (sensor.type === this.TIRE)  // tire pressure
      this.setTireRecordDto(sensor);
    else
      this.setRecordDto(sensor);
  }

  private setGeneralRecordDto(carVin: string, sensorType: string): void {
    let recordDto = this.recordDto;
    recordDto.date = this.getRandomDate();
    recordDto.carVin = carVin;
    recordDto.sensorType = sensorType;
  }

  private setRecordDto(sensor: Sensor): void {
    this.recordDto.value = this.getRandomDouble(sensor.minValue, sensor.maxValue);
    this.url = this.SENSOR_URL;
  }

  private setTireRecordDto(sensor: Sensor): void {
    let tires: Tires = new Tires();
    for (let prop in tires){
      tires[prop] = this.getRandomDouble(sensor.minValue, sensor.maxValue);
    }

    this.recordDto.value = tires;
    this.url = this.TIRE_URL;
  }

  private getSensorByType(sensorType: string): Sensor {
    for (let index in SENSORS) {
      let sensor: Sensor = SENSORS[index];
      if (sensor['type'] === sensorType)
        return sensor;
    }
  }


  // ***********
  // RANDOM DATA
  // ***********

  private getRandomInt(min: number, max: number): number {
    return Math.floor(Math.random() * (max - min + 1)) + min;
  }

  private getRandomDouble(min: number, max: number): number {
    return Number((Math.random() * (max - min + 1) + min).toFixed(4));
  }

  private getRandomDate(): string {

    const MIN_YEAR: number = 2019;
    const MIN_MONTH_DAY: number = 1;
    const MIN_TIME: number = 0;

    const MAX_YEAR: number = 2019;
    const MAX_MONTH: number = 2;
    const MAX_HOUR: number = 23;
    const MAX_MINUTES_SECONDS: number = 59;

    let year: number = this.getRandomInt(MIN_YEAR, MAX_YEAR);
    let month: number = this.getRandomInt(MIN_MONTH_DAY, MAX_MONTH);

    const MAX_DAY = 13;

    let day: number = this.getRandomInt(MIN_MONTH_DAY, MAX_DAY);
    let hour: number = this.getRandomInt(MIN_TIME, MAX_HOUR);
    let minutes: number = this.getRandomInt(MIN_TIME, MAX_MINUTES_SECONDS);
    let seconds: number = this.getRandomInt(MIN_TIME, MAX_MINUTES_SECONDS);

    return this.dateToMySqlString(new Date(year, month, day, hour, minutes, seconds));
  }

  private dateToMySqlString(date: Date): string{
    let str: string = date.toISOString();
    return str.slice(0, 10) + ' ' + str.slice(11, 19);
  }

  private getLastDayInMonth(month: number, year: number): number {

    const MONTH_31: number[] = [1, 3, 5, 7, 8, 10, 12];
    const MONTH_30: number[] = [4, 6, 9, 11];

    if (MONTH_31.indexOf(month) !== -1) {
      return 31;
    } else if (MONTH_30.indexOf(month) !== -1) {
      return 30;
    } else {
      if (year % 4 === 0)
        return 29;
      else
        return 28;
    }
  }

}
