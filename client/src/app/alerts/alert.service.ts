import { Injectable } from '@angular/core';
import {MatDialog} from '@angular/material';
import { AlertsComponent } from './alerts.component';

@Injectable({
  providedIn: 'root'
})
export class AlertService {

  constructor(private dialog: MatDialog) { }
/*
  openCongirmDialog(msg){
    this.dialog.open(AlertsComponent,{
      width: '390px',
      panelClass: 'confirm-dialog-container',
      disableClose: true,
      position: { top: "10px" },
      data :{
        message : msg
      }
    });
  }
  */
}
