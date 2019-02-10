import { Component, OnInit } from '@angular/core';
import{Car} from './car';
import { CarsService } from './cars.service';

@Component({
  selector: 'app-cars',
  templateUrl: './cars.component.html',
  styleUrls: ['./cars.component.scss']
})
export class CarsComponent implements OnInit {

  cars: Car[];
  car1: Car;

  constructor(private carsService: CarsService) { }
  
  ngOnInit(){
this.carsService.getOwnerCarsById(4)
 .subscribe(data => this.cars = data);

this.carsService.getCarById(2) 
.subscribe(data => this.car1 = data);

console.log(this.cars);
console.log(this.car1);
  }

    
    applyToSTO(id: number){

    }

    applyToTradeIn (id: number){

    }

    wiewCharts(id: number){

    }

}
