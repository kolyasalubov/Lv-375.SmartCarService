import { Component, OnInit, Input } from '@angular/core';
import {Car} from './car';
import { CarsService } from './cars.service';
import { User } from '../users/user';
import { TokenStorageService } from '../auth/token-storage.service';
import { UsersService } from '../users/users.service';
import { identifierModuleUrl } from '@angular/compiler';
import { ActivatedRoute, Router } from '@angular/router';
import { AlertService } from '../alerts/alert.service';



@Component({
  selector: 'app-cars',
  templateUrl: './cars.component.html',
  styleUrls: ['./cars.component.scss']
})
export class CarsComponent implements OnInit {

  cars: Car[];
  private username: String;
  user: User;
  car: Car;
  showCards: boolean = false;
  showProfile: boolean = false;
  userId: Number;
  errorCode: number;
  error: ErrorEvent;

  constructor(private carsService: CarsService, private userService: UsersService, private tokenStorage: TokenStorageService, private route: ActivatedRoute, private router: Router, private alertService: AlertService) { }

  ngOnInit(){

  this.carsService.getOwnerCarsByUsername(this.tokenStorage.getUsername())
  .subscribe(data => this.cars = data);

   setTimeout(() => {
    if(this.cars == null){
      if(confirm("You don't have registered car")) {
    }
     } else if(this.cars.length > 1){
     this.showCards = true;
     } else{
       this.showProfile = true;
     }; }, 1000);

}

    deleteCarById(id: number){
    if(confirm("Are you sure to delete this car? Note, it can't be restored.")) {
    this.carsService.deleteCarById(id).subscribe();
    this.reloadPage();
    }
  }

    applyToSTO(id: number){
    this.router.navigate(['/ui/booking', id]);
    }

  applyToTradeIn(vin: String){
    console.log(vin);
    this.router.navigate(['ui/tradeIn',vin]);
  }

  goToCharts(car: Car){
      this.router.navigate(['/ui/charts'],
        {queryParams: {
          carId: car.id,
          carVin: car.vin
        }}
      );
  }

    history(){
    //this.alertService.openCongirmDialog();
    }

    reloadPage() {
      window.location.href='/ui/cars/';
    }

    closeProfile(){
      window.location.href='/ui/home'
    }

    openProfile(car: Car){
      this.router.navigate(['ui/carprofile'],
      {queryParams: {
        carId: car.id,
        carBrand: car.brand,
        carModel: car.model,
        carGY: car.graduation_year,
        carNumber: car.number,
        carVin: car.vin,
        carPrice:car.price,
        carEnd_guarantee: car.end_guarantee
      }}
    );
    }

    getCarById (id: number){
     this.carsService.getCarById(id).subscribe(data => this.car = data);
     console.log(this.car);
    }

    getCarByNumber(number: String){
      this.carsService.getCarByNumber(number).subscribe(data => this.car = data);
      console.log(this.car);
    }

    getCarByVin(vin: String){
      this.carsService.getCarByVin(vin).subscribe(data => this.car = data);
      console.log(this.car);
    }

}
