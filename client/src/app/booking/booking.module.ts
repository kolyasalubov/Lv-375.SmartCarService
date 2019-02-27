import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BookingComponent } from './booking.component';
import { StoSkillComponent } from './skills/skills.component';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { WorkerListComponent } from './worker-list/worker-list.component';
import { TimeListComponent } from './time-list/time-list.component';
import { FormsModule } from '@angular/forms';
@NgModule({
  declarations: [
    BookingComponent,
    StoSkillComponent,
    WorkerListComponent,
    TimeListComponent],
  exports : [BookingComponent],
  bootstrap : [BookingComponent],
  imports: [
    MDBBootstrapModule.forRoot(),
    CommonModule,
    FormsModule
  ]
})
export class BookingModule { }
