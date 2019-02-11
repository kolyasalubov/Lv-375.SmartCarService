import { Component, OnInit } from '@angular/core';
import { Car } from '../cars/car';
import { OwnerCar } from './owner-car';
import { CarProfileService } from './car-profile.service';

@Component({
  selector: 'app-car-profile',
  templateUrl: './car-profile.component.html',
  styleUrls: ['./car-profile.component.scss']
})
export class CarProfileComponent implements OnInit {

  carProfile: any = {};
  car: OwnerCar;

  constructor(private carService: CarProfileService ) { }

  ngOnInit() {
  }

  onSubmit(){
    console.log(this.carProfile);

    this.car = new OwnerCar(
      this.carProfile.brand,
      this.carProfile.model,
      this.carProfile.graduation_year,
      this.carProfile.number,
      this.carProfile.vin);

      console.log(this.car);

this.carService.createCar(this.car);
    }
    
}
