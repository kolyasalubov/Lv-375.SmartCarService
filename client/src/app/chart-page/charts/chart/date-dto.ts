export class DateDto {

  sensorType: string;
  carId: number;
  date: string;

  constructor(sensorType: string, carId: number, date: string) {
    this.sensorType = sensorType;
    this.carId = carId;
    this.date = date;
  }

}
