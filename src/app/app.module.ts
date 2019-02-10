import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { LineChartComponent } from 'src/charts/line-chart.component';

import { AppRoutingModule } from './app-routing.module';
import { SecurityModule } from './security/security.module';
import { AppComponent } from './app.component';
import { TechmanagerProfileComponent } from './techmanager-profile/techmanager-profile.component';
import { TechmanagerProfileService } from './techmanager-profile/techmanager-profile.service';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { TechserviceComponent } from './techservice/techservice.component';
import { WorkerComponent } from './techservice/worker/worker.component';
import { SkillComponent } from './techservice/worker/skill/skill.component';
import { MenuComponent } from './menu/menu.component';
import { CarsComponent } from './cars/cars.component';
import { UsersComponent } from './users/users.component';
import { InfoComponent } from './info/info.component';
import { CarProfileComponent } from './car-profile/car-profile.component';
import { UserProfileComponent } from './user-profile/user-profile.component';


@NgModule({
  declarations: [
    AppComponent,
    LineChartComponent,
    TechmanagerProfileComponent,
    TechserviceComponent,
    WorkerComponent,
    SkillComponent,
    MenuComponent,
    CarsComponent,
    UsersComponent,
    InfoComponent,
    CarProfileComponent,
    UserProfileComponent,
    
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
    MDBBootstrapModule.forRoot(),
    SecurityModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }