import { Component, OnInit, Input } from '@angular/core';
import {Car} from './car';
import { CarsService } from './cars.service';
import { User } from '../users/user';
import { TokenStorageService } from '../auth/token-storage.service';
import { UsersService } from '../users/users.service';
import { identifierModuleUrl } from '@angular/compiler';
import { ActivatedRoute, Router } from '@angular/router';



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
  
  @Input()
  id: number;


  constructor(private carsService: CarsService, private userService: UsersService, private tokenStorage: TokenStorageService, private route: ActivatedRoute, private router: Router) { }
  
  ngOnInit(){
   this.route.params.subscribe(params => {
    this.id = params["id"];
});

  this.carsService.getOwnerCarsById(this.id)
  .subscribe(data => this.cars = data);
  
   
 setTimeout(() => { 
    if(this.cars.length > 1){
   this.showCards = true;
   } else{
     this.showProfile = true;
   }; }, 1000);
   console.log(this.cars.length);
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

    applyToTradeIn (id: number){

    }

    goToCharts(car: Car){
      this.router.navigate(['/ui/charts'], 
        {queryParams: {
          carId: car.id, 
          carVin: car.vin
        }}
      );
    }

    history(id: number){

    }

    reloadPage() {
      window.location.href='/ui/ownercars/' + this.id;
    }

    closeProfile(){
      window.location.href='/ui/home'
    }

    openProfile(car: Car){
      this.car = car;
    //  this.carsService.getCarById(id).subscribe(data => this.car = data);
      this.showCards = false;
      this.showProfile = true;
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
