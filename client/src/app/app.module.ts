import {BrowserModule} from '@angular/platform-browser';
import {NgModule, NO_ERRORS_SCHEMA} from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';

import {MDBBootstrapModule, PopoverModule} from 'angular-bootstrap-md';

import {AppRoutingModule} from './app-routing.module';
import {SecurityModule} from './security/security.module';
import {AppComponent} from './app.component';
import {TechmanagerProfileComponent} from './techmanager-profile/techmanager-profile.component';
import {TechmanagerProfileService} from './techmanager-profile/techmanager-profile.service';
import {ChartPageModule} from './chart-page/chart-page.module';
import {RolesComponent} from './roles/roles.component';
import {TechserviceComponent} from './techservice/techservice.component';
import {WorkerComponent} from './techservice/worker/worker.component';
import {BookingModule} from './booking/booking.module';
import {SkillComponent} from './techservice/worker/skill/skill.component';
import {MenuComponent} from './menu/menu.component';
import {CarsComponent} from './cars/cars.component';
import {UsersComponent} from './users/users.component';
import {InfoComponent} from './info/info.component';
import {CarProfileComponent} from './car-profile/car-profile.component';
import {UserProfileComponent} from './user-profile/user-profile.component';
import {NotificationsService} from  './notifications/notifications.service';
import {NotificationsApprovementComponent} from './notifications/notifications-approvement/notifications-approvement.component';
import {NotificationsListComponent} from './notifications/notifications-list/notifications-list.component';
import {MapComponent} from './techservice/map/map.component';
import {AgmCoreModule} from '@agm/core';
import {LoginComponent} from './login/login.component';
import { CarTrackerComponent } from './cars/car-tracker/car-tracker.component';
import { CarDetailsComponent } from './cars/car-details/car-details.component';
import { ServicesFeedbackFormComponent} from './services-feedback-form/services-feedback-form.component';
import { WorkersFeedbackComponent } from './workers-feedback/workers-feedback.component';
import { ServicesFeedbackComponent } from './services-feedback/services-feedback.component';
import { DealerComponent } from './dealer/dealer.component';
import { DealcarComponent } from './dealer/dealcar/dealcar.component';
import { DealerstoaddComponent } from './dealer/dealerstoadd/dealerstoadd.component';
import { DealercarsComponent } from './dealer/dealercars/dealercars.component';
import { DealerstosComponent } from './dealer/dealerstos/dealerstos.component';
import { TradesinComponent } from './dealer/tradesin/tradesin.component';
import { TradeInComponent } from './trade-in/trade-in.component';
import { PreviewComponent } from './preview/preview.component';
import { ProgresbarComponent } from './progresbar/progresbar.component';
import { AlertsComponent } from './alerts/alerts.component';
import * as Material from '@angular/material';
import { MatDialog, MatDialogModule } from '@angular/material';
import { OverlayModule } from '@angular/cdk/overlay';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { DatePipe } from '@angular/common';
import { DataCardComponent } from './cars/data-card/data-card.component';
import { TechserviceCardComponent } from './techservice-card/techservice-card.component';



@NgModule({
  declarations: [
    AppComponent,
    TechmanagerProfileComponent,
    TechmanagerProfileComponent,
    TechserviceComponent,
    WorkerComponent,
    RolesComponent,
    SkillComponent,
    RolesComponent,
    MenuComponent,
    CarsComponent,
    UsersComponent,
    InfoComponent,
    CarProfileComponent,
    UserProfileComponent,
    NotificationsApprovementComponent,
    NotificationsListComponent,
    MapComponent,
    CarTrackerComponent,
    CarDetailsComponent,
    ServicesFeedbackFormComponent,
    ServicesFeedbackComponent,
    DealerComponent,
    DealcarComponent,
    DealerstoaddComponent,
    DealercarsComponent,
    DealerstosComponent,
    TradesinComponent,
    TradeInComponent,
    PreviewComponent,
    ProgresbarComponent,
    AlertsComponent,
    DataCardComponent,
    TechserviceCardComponent,
   // Material.MatDialogModule

  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
    MDBBootstrapModule.forRoot(),
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyAojOwUL0HAte_4FqR1pIgXdRIMQ82-ev0'
    }),
    BookingModule,
    ChartPageModule,
    SecurityModule,
    OverlayModule,
    MatDialogModule,
    BrowserAnimationsModule
  ],
  providers: [
    //NotificationsService
    MatDialog,
    DatePipe
  ],
  schemas: [NO_ERRORS_SCHEMA],
  bootstrap: [AppComponent],
  exports: [SecurityModule],
  entryComponents:[AlertsComponent]
  })
export class AppModule {
}
