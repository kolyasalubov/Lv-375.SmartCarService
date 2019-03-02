import { Component, OnInit } from '@angular/core';
import { Techservice } from '../techservice/techservice';
import { User } from '../users/user';
import { TokenStorageService } from '../auth/token-storage.service';
import { UsersService } from '../users/users.service';
import { TechserviceService } from '../techservice/techservice.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'services-feedback-page',
  templateUrl: './services-feedback-page.component.html',
  styleUrls: ['./services-feedback-page.component.scss']
})
export class ServicesFeedbackPageComponent implements OnInit {

  techserviceId: number;

  constructor(private route: ActivatedRoute) {}

  ngOnInit() {
    this.techserviceId = Number.parseInt( this.route.snapshot.paramMap.get('id'));
    console.log(this.techserviceId);
  }
}
