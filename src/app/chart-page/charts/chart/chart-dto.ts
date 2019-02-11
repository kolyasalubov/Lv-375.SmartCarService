export class ChartDto {

    data: number[];
    labels: string[];

    constructor(data: number[], labels: string[] ){
        this.data = data;
        this.labels = labels;
    }
     
}
