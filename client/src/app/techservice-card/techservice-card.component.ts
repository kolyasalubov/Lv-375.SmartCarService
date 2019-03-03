import { Component, OnInit, Input } from '@angular/core';
import { Techservice } from '../techservice/techservice';

@Component({
  selector: 'app-techservice-card',
  templateUrl: './techservice-card.component.html',
  styleUrls: ['./techservice-card.component.scss']
})
export class TechserviceCardComponent implements OnInit {

  @Input() techservice: Techservice;

  constructor() { }

  ngOnInit() {
  }

}
