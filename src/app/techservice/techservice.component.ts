import { Component, OnInit } from '@angular/core';
import { Techservice } from './techservice';
import { TechserviceService } from './techservice.service';

import { TokenStorageService } from '../auth/token-storage.service';
import { User } from '../users/user';
import { UsersService } from '../users/users.service';

@Component({
  selector: 'app-techservice',
  templateUrl: './techservice.component.html',
  styleUrls: ['./techservice.component.scss']
})
export class TechserviceComponent implements OnInit {

  techservice: Techservice = {name: '', address: '', workers:[], dealer: null, techManager: null};
  error: ErrorEvent;

  user: User;

  constructor(private techserviceService: TechserviceService,
    private tokenStorage: TokenStorageService, private userService: UsersService) { }

  ngOnInit() {
    this.userService.getUserByUsername(this.tokenStorage.getUsername())
    .subscribe(data => this.user = data);
  }

  createTechservice(techservice: Techservice) {
    this.techserviceService.createTechnicalService(techservice, this.user.id)
    .subscribe();
  }

}
