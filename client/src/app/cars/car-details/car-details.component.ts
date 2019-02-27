import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Car } from '../car';
import { CarsService } from '../cars.service';

@Component({
  selector: 'app-car-details',
  templateUrl: './car-details.component.html',
  styleUrls: ['./car-details.component.scss']
})
export class CarDetailsComponent implements OnInit {

  carId: Number;
  carBrand: String;
  carModel: String;
  carGY: String;
  carNumber: String;
  carVin: String;
  carPrice: String;
  carEnd_guarantee: String;
 
  
  constructor(private route: ActivatedRoute, private router: Router, private carsService: CarsService,) { }

  ngOnInit() {
    
  setTimeout(() => 
    {this.carId = Number(this.getFromRouterParams('carId'));
    this.carBrand = this.getFromRouterParams('carBrand');
    this.carModel = this.getFromRouterParams('carModel');
    this.carGY = this.getFromRouterParams('carGY');
    this.carNumber = this.getFromRouterParams('carNumber');
    this.carVin = this.getFromRouterParams('carVin');
    this.carPrice = this.getFromRouterParams('carPrice');
    this.carEnd_guarantee = this.getFromRouterParams('carEnd_guarantee');
  }, 1000);

    setTimeout(() => 
    { console.log(this.carId, this.carBrand, this.carModel, this.carGY, this.carNumber, this.carVin) 
    }, 1000);

    }

  private getFromRouterParams(param: string) {
    return this.route.snapshot.queryParamMap.get(param);
  }


  deleteCarById(id: number){
    if(confirm("Are you sure to delete this car? Note, that it can't be restored.")) {
  this.carsService.deleteCarById(id).subscribe();
  this.closeProfile();
  }
}

  applyToSTO(id: number){
  this.router.navigate(['ui/booking', id]);
  }

  applyToTradeIn (id: number){

  }

  goToCharts(carId: Number, carVin: String){
    this.router.navigate(['ui/charts'], 
      {queryParams: {
        carId: carId, 
        carVin: carVin
      }}
    );
  }

  history(id: number){

  }

  closeProfile(){
    window.location.href='ui/home'
  }

}
