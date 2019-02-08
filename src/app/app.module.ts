import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { LineChartComponent } from 'src/charts/line-chart.component';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TechmanagerProfileComponent } from './techmanager-profile/techmanager-profile.component';
import { TechmanagerProfileService } from './techmanager-profile/techmanager-profile.service';
import { MDBBootstrapModule } from 'angular-bootstrap-md';

@NgModule({
  declarations: [
    AppComponent,
    LineChartComponent,
    TechmanagerProfileComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
    MDBBootstrapModule.forRoot()
  ],
  providers: [TechmanagerProfileService],
  bootstrap: [AppComponent]
})
export class AppModule { }