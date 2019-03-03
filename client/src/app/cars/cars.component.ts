import { Component, OnInit, Input } from '@angular/core';
import {Car} from './car';
import { CarsService } from './cars.service';
import { User } from '../users/user';
import { TokenStorageService } from '../auth/token-storage.service';
import { UsersService } from '../users/users.service';
import { ChartService } from '../chart-page/charts/chart/chart.service';
import { identifierModuleUrl } from '@angular/compiler';
import { ActivatedRoute, Router } from '@angular/router';
import { AlertService } from '../alerts/alert.service';

import { ChartData } from '../chart-page/charts/chart/chart-data';

@Component({
  selector: 'app-cars',
  templateUrl: './cars.component.html',
  styleUrls: ['./cars.component.scss'],
  providers: [ChartService]
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

  @Input()
  id: number;

  // mileage: number;
  // speed: number;

  constructor(private carsService: CarsService, private userService: UsersService, private tokenStorage: TokenStorageService, private route: ActivatedRoute, private router: Router, private chartService: ChartService) { }

  ngOnInit(){


/*
    this.username = this.tokenStorage.getUsername();
    this.userService.getUserByUsername(this.username)
    .subscribe(data => this.user = data);
*/
   this.route.params.subscribe(params => {
    this.id = params["id"];
});

    //TODO move in another component

    // this.chartService.getChartData("mileage", this.id, "/last") //TODO change id to car.id
    //   .subscribe(
    //     data => {
    //       let chartData: ChartData = new ChartData();
    //       chartData.setChartData(data);
    //       this.mileage = chartData.data[0];
    //     },
    //     error => console.error('Error: ', error)
    //   );
    //
    // this.chartService.getChartData("speed", this.id, "/day/max") //TODO change id to car.id
    //   .subscribe(
    //     data => {
    //       let chartData: ChartData = new ChartData();
    //       chartData.setChartData(data);
    //       this.speed = Math.round(chartData.data[0]);
    //     },
    //     error => console.error('Error: ', error)
    //   );

/*if(this.user.id != this.id){
  if(confirm("Such requekst is not allowed")) {
  } else {
    this.carsService.getOwnerCarsById(this.id)
    .subscribe(data => this.cars = data);
  }
}   */

    this.carsService.getOwnerCarsByUsername(this.tokenStorage.getUsername())
      .subscribe(data => {
        this.cars = data;
        if (this.cars == null) {
          // this.showProposal = true;
          window.alert("There are no cars!")
        } else if (this.cars.length > 1) {
          this.showCards = true;
        } else {
          this.showProfile = true;
        }
      });
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
