import { BrowserModule } from '@angular/platform-browser';
import { NgModule, NO_ERRORS_SCHEMA } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { MDBBootstrapModule } from 'angular-bootstrap-md';

import { AppRoutingModule } from './app-routing.module';
import { SecurityModule } from './security/security.module';
import { AppComponent } from './app.component';

import { TechmanagerProfileComponent } from './techmanager-profile/techmanager-profile.component';
import { TechmanagerProfileService } from './techmanager-profile/techmanager-profile.service';


import { ChartPageModule } from './chart-page/chart-page.module';

import { TechserviceComponent } from './techservice/techservice.component';
import { WorkerComponent } from './techservice/worker/worker.component';
import { SkillComponent } from './techservice/worker/skill/skill.component';
import { RolesComponent } from './roles/roles.component';

@NgModule({
  declarations: [
    AppComponent,
    TechmanagerProfileComponent,
    TechmanagerProfileComponent,
    TechserviceComponent,
    WorkerComponent,
    SkillComponent,
    RolesComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
    MDBBootstrapModule.forRoot(),
    
    ChartPageModule,
    SecurityModule
  ],
  providers: [],
  schemas: [NO_ERRORS_SCHEMA],
  bootstrap: [AppComponent]
})
export class AppModule { }