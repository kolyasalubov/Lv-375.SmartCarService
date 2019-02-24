import {Component, OnInit} from '@angular/core';
import {Car} from '../cars/car';
import {OwnerCar} from './owner-car';
import {CarProfileService} from './car-profile.service';
import {TokenStorageService} from '../auth/token-storage.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-car-profile',
  templateUrl: './car-profile.component.html',
  styleUrls: ['./car-profile.component.scss']
})
export class CarProfileComponent implements OnInit {

  carProfile: any = {};
  car: OwnerCar;
  username: String;
  years: any[];


  constructor(private carService: CarProfileService, private tokenStorage: TokenStorageService, private router: Router) {
  }

  ngOnInit() {
    this.username = this.tokenStorage.getUsername();
    this.years = [2019, "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997"];
  }

  onSubmit() {
    this.carService.createCar(this.carProfile, this.username).subscribe();
    console.log(this.carProfile.graduationyear);
    this.reloadPage();

  }

  reloadPage() {
    window.location.href = '/ui/home';
  }


}
