import { WorkInfo } from '../worker-list/work-info';

export class NewBooking{
    workerId : Array<number>;
    start : string;
    carId : number;
    workInfo : Array<WorkInfo>;
}