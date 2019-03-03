import { Component, OnInit } from '@angular/core';
import { OwnerCar } from './owner-car';
import { CarProfileService } from './car-profile.service';
import { TokenStorageService } from '../auth/token-storage.service';
import { Router } from '@angular/router';
import {VechicleInspection} from './vechicle-inspection';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { FormGroup, FormControl } from '@angular/forms';
import { DatePipe } from '@angular/common';

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
  carVin: String;
  carNumber: String;
  carVechicleInspection: any = {};
  showCarProfile: boolean = true;
  showInspection: boolean = false;
  errorCode: number;
  error: ErrorEvent;

  constructor(private carService: CarProfileService, private tokenStorage: TokenStorageService, private router: Router, private datePipe: DatePipe) { }

  ngOnInit() {
    this.username = this.tokenStorage.getUsername();
    this.years = ["2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997"];
  }

  onSubmit(){

    this.carService.addCar(this.carProfile, this.username)
    .subscribe(error => {
      this.errorCode = error.status,
      error => this.error = error;
      
      if(this.errorCode === 201){
      this.showCarProfile = false;
      this.showInspection = true;
      } else if(this.errorCode === 400){
        console.log(this.errorCode)
      if(confirm("Car with such registration or vin number is present in our system. Please check your data.")) {
        }
      } else {
      if(confirm("Something bad happened; please try again later")) {}
    }
  });
  }

 

  Confirm(){
    this.carVechicleInspection.dateOfInspection == "" ? "" : this.datePipe.transform(this.carVechicleInspection.dateOfInspection, 'yyyy-MM-dd')
    this.carService.createInspection(this.carVechicleInspection, this.carVin).subscribe();
    this.reloadPage();
  }

  reloadPage() {
    window.location.href='/ui/home';
  }
}
