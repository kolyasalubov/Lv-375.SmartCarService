import { Worker } from './worker';


export class WorkersTime{
    workerList : Map<string, Array<Worker>>;
    requiredTime : number;
}