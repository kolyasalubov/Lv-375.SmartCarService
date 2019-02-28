import { Worker } from './worker';
import { WorkInfo } from './work-info';


export class WorkersTime{
    workInfo : Array<WorkInfo>;
    workerList : Map<string, Array<Worker>>;
    requiredTime : number;
}