import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BookingComponent } from './booking.component';
import { StoSkillComponent } from './skills/skills.component';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { WorkerListComponent } from './worker-list/worker-list.component';

@NgModule({
  declarations: [
    BookingComponent,
    StoSkillComponent,
    WorkerListComponent],
  exports : [BookingComponent],
  bootstrap : [BookingComponent],
  imports: [
    MDBBootstrapModule.forRoot(),
    CommonModule,
  ]
})
export class BookingModule { }
