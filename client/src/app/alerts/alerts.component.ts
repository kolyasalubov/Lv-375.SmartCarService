import { Component, OnInit, ViewChild } from '@angular/core';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import { ActivatedRoute, Router } from '@angular/router';
import {MatDialog} from '@angular/material';

@Component({
  selector: 'app-alerts',
  templateUrl: './alerts.component.html',
  styleUrls: ['./alerts.component.scss']
})
export class AlertsComponent implements OnInit {

 
  constructor(private router: Router) { }

  ngOnInit() {
  }

  goToCar(){
    this.router.navigate(['ui/message'])
  }

}
