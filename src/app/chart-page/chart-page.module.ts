import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';

import { MDBBootstrapModule } from 'angular-bootstrap-md';

import { ChartPageComponent } from './chart-page.component';
import { FakeDataBtnComponent } from './fake-data-btn/fake-data-btn.component';
import { LineChartComponent } from './charts/line-chart/line-chart.component';
import { BarChartComponent } from './charts/bar-chart/bar-chart.component';
import { DoughnutBarChartComponent } from './charts/doughnut-bar-chart/doughnut-bar-chart.component';
import { ChartComponent } from './charts/chart/chart.component';

@NgModule({
  declarations: [
    ChartPageComponent,
    FakeDataBtnComponent,
    LineChartComponent,
    BarChartComponent,
    DoughnutBarChartComponent,
    ChartComponent,
  ],
  imports: [
    CommonModule,
    MDBBootstrapModule.forRoot(),
    HttpClientModule,
  ],
  bootstrap: [ChartPageComponent],
  exports: [ChartPageComponent]
})
export class ChartPageModule { }
