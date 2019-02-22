import { Component, OnInit } from '@angular/core';
import { Techservice } from './techservice';
import { TechserviceService } from './techservice.service';

@Component({
  selector: 'app-techservice',
  templateUrl: './techservice.component.html',
  styleUrls: ['./techservice.component.scss']
})
export class TechserviceComponent implements OnInit {

  techservice: Techservice = {name: '', address: '', workers:[], dealer: null, techManager: null};
  error: ErrorEvent;

  constructor(private techserviceService: TechserviceService) { }

  ngOnInit() {
  }

  createTechservice(techservice: Techservice) {
    this.techserviceService.createTechnicalService(techservice)
    .subscribe();
  }

}
