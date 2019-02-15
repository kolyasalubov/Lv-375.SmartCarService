export class ChartDto {

    data: number[];
    labels: string[];

    constructor(data: number[] = null, labels: string[] = null){
        this.data = data;
        this.labels = labels;
    }
     
}
