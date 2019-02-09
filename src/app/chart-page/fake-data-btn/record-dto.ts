import { Tires } from './tires'

export class RecordDto {

    sensorType: string;
    carVin: string;
    date: string;
    value: number | Tires;
     
}
