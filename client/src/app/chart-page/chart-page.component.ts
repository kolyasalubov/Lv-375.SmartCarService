import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-chart-page',
  templateUrl: './chart-page.component.html',
  styleUrls: ['./chart-page.component.scss']
})
export class ChartPageComponent implements OnInit {

  carId: number;
  carVin: string;

  constructor(private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.carId = Number(this.getFromRouterParams('carId'));
    this.carVin = this.getFromRouterParams('carVin');
  }

  private getFromRouterParams(param: string) {
    return this.route.snapshot.queryParamMap.get(param);
  }

}
