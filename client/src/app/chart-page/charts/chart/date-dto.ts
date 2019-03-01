export class DateDto {

  sensorType: string;
  carId: number;
  date: string;
  selection: string;

  constructor(sensorType: string, carId: number, date: string, selection: string) {
    this.sensorType = sensorType;
    this.carId = carId;
    this.date = date;
    this.selection = selection;
  }

}
