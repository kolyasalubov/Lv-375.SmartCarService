import {Component, OnInit} from '@angular/core';
import {UserComponent} from '../user/user.component';
import {UsersService} from '../users/users.service';
import {User} from '../users/user';
import {TokenStorageService} from '../auth/token-storage.service';
import { CarsService } from '../cars/cars.service';
import { Car } from '../cars/car';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {

  userProfile: User = new User(null, null, null, null, null, null);
  username: String;
  cars: Car[];
  numberOfCars: Number = null;

  constructor(private userService: UsersService, private tokenStorage: TokenStorageService, private carService: CarsService) {
  }

  ngOnInit() {

this.username = this.tokenStorage.getUsername();

this.userService.getUserByUsername(this.username)
.subscribe(data => this.userProfile = data);

setTimeout(() => {
this.carService.getOwnerCarsById(this.userProfile.id)
.subscribe(data => this.cars = data);
}, 1000);

   setTimeout(() => {
     this.numberOfCars = this.cars.length;
      }, 1000);
  }

  closeProfile(){
    window.location.href='ui/home'
  }


}
