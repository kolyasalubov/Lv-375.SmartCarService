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
  private sub: any;

  @Input()
  id: number;


  constructor(private carsService: CarsService, private userService: UsersService, private tokenStorage: TokenStorageService, private route: ActivatedRoute) { }
  
  ngOnInit(){

    this.sub = this.route.params.subscribe(params => {
      let id = + params['id'];  
      this.carsService.getOwnerCarsById(id)
      .subscribe( data => this.cars = data); 
   });

 //  let id = this.route.snapshot.params['id'];
/*
this.username = this.tokenStorage.getUsername();

console.log(this.username);

this.userService.getUserByUsername(this.username)
.subscribe(data => this.user = data);
*/
console.log(this.id);
console.log(this.sub);

 this.carsService.getOwnerCarsById(2)
 .subscribe(data => this.cars = data);

 }
   
    applyToSTO(id: number){

    }

    applyToTradeIn (id: number){

    }

    wiewCharts(id: number){

    }

    history(id: number){

    }

}
