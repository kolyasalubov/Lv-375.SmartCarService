import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MDBBootstrapModule } from 'angular-bootstrap-md';

import { LineChartComponent } from './charts/line-chart/line-chart.component';
import { FakeDataBtnComponent } from './fake-data-btn/fake-data-btn.component';
import { ChartPageComponent } from './chart-page.component';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    ChartPageComponent,
    LineChartComponent,
    FakeDataBtnComponent,
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
