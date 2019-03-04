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
import { MatDialog } from '@angular/material';
import { AlertsComponent } from '../alerts/alerts.component';
import { ChartData } from '../chart-page/charts/chart/chart-data';
import { JsonPipe } from '@angular/common';
import { InfoMassageComponent } from '../info-massage/info-massage.component';

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
  showProposal: boolean = false;
  userId: Number;
  httpStatusCode: number;
  error: ErrorEvent;

  // mileage: number;
  // speed: number;

  constructor(private carsService: CarsService, private userService: UsersService, private tokenStorage: TokenStorageService, private route: ActivatedRoute, private router: Router, private alertService: AlertService,  public dialog: MatDialog, private chartService: ChartService) { }

  ngOnInit(){
  this.carsService.getOwnerCarsByUsername(this.tokenStorage.getUsername())
  .subscribe(data => {
    this.cars = data,
    error => this.error = error;

    if(this.cars.length === 1){
      this.showProfile = true;  
    } else if(this.cars.length > 1){
      this.showCards = true;
    } else {
      this.showProposal = true;
    }
  },);
}

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
          carId: car.id
        }}
      );
  }

  history(){
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

    deleteCarById(id: Number): void {
      const dialogRef = this.dialog.open(AlertsComponent, {
        height: '150px',
        width: '400px',
        data: id
      });
  
      dialogRef.afterClosed().subscribe(result => {
      });
    }
    
}
