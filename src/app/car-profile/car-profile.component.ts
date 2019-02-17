import { Component, OnInit } from '@angular/core';
import { Car } from '../cars/car';
import { OwnerCar } from './owner-car';
import { CarProfileService } from './car-profile.service';
import { TokenStorageService } from '../auth/token-storage.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-car-profile',
  templateUrl: './car-profile.component.html',
  styleUrls: ['./car-profile.component.scss']
})
export class CarProfileComponent implements OnInit {

  carProfile: any = {};
  car: OwnerCar;
  username: String;

  constructor(private carService: CarProfileService, private tokenStorage: TokenStorageService, private router: Router ) { }

  ngOnInit() {
    this.username = this.tokenStorage.getUsername();
  }

  onSubmit(){
    this.carService.createCar(this.carProfile, this.username).subscribe();
    this.reloadPage();
  }

  reloadPage() {
    window.location.href='/home';
  }


}