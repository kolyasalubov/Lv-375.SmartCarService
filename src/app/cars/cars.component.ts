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
  
  @Input()
  id: number;


  constructor(private carsService: CarsService, private userService: UsersService, private tokenStorage: TokenStorageService, private route: ActivatedRoute, private router: Router) { }
  
  ngOnInit(){
   this.route.params.subscribe(params => {
    this.id = params["id"];
});

 this.carsService.getOwnerCarsById(this.id)
 .subscribe(data => this.cars = data);

 }
   
    deleteCarById(id: number){
    this.carsService.deleteCarById(id).subscribe();
    this.reloadPage();
 }

    applyToSTO(id: number){
    this.router.navigate(['/booking', id]);
    }

    applyToTradeIn (id: number){

    }

    goToCharts(car: Car){
      this.router.navigate(['/charts'], 
        {queryParams: {
          carId: car.id, 
          carVin: car.vin
        }}
      );
    }

    history(id: number){

    }

    reloadPage() {
      window.location.href='/ownercars/' + this.id;
    }

}
