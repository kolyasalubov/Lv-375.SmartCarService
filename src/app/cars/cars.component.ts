import { Component, OnInit, Input } from '@angular/core';
import {Car} from './car';
import { CarsService } from './cars.service';
import { User } from '../users/user';
import { TokenStorageService } from '../auth/token-storage.service';
import { UsersService } from '../users/users.service';
import { identifierModuleUrl } from '@angular/compiler';
import { ActivatedRoute } from '@angular/router';



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


  constructor(private carsService: CarsService, private userService: UsersService, private tokenStorage: TokenStorageService, private route: ActivatedRoute) { }
  
  ngOnInit(){

   this.route.params.subscribe(params => {
    this.id = params["id"];
});

 this.carsService.getOwnerCarsById(this.id)
 .subscribe(data => this.cars = data);

 }
   
 deleteCarById(id: number){
this.carsService.getCarById(id);
this.reloadPage();

 }
    applyToSTO(id: number){

    }

    applyToTradeIn (id: number){

    }

    wiewCharts(id: number){

    }

    history(id: number){

    }

    reloadPage() {
      window.location.href='/ownercars/' + this.id;
    }

}
