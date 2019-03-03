import { Component, OnInit } from '@angular/core';
// import { TokenStorageService } from '../auth/token-storage.service';
import { TokenStorageService } from 'src/app/auth/token-storage.service';
import { DealerSto } from './dealersto';
import{DealerstoaddService} from './dealerstoadd.service';
import { from } from 'rxjs';
import { Techservice } from 'src/app/techservice/techservice';
@Component({
  selector: 'app-dealerstoadd',
  templateUrl: './dealerstoadd.component.html',
  styleUrls: ['./dealerstoadd.component.scss']
})
export class DealerstoaddComponent implements OnInit {
  username: String;
  Techservice

// techserviceStub: DealerSto = { name: '', address: ''};
// sto: DealerSto = this.techserviceStub;


techserviceStub: Techservice = new Techservice();
sto: Techservice = this.techserviceStub;

  constructor(private tokenStorage: TokenStorageService,private dealerstoaddService:DealerstoaddService) { }


  ngOnInit() {
    this.username = this.tokenStorage.getUsername();
  }

  createSto(){
console.log(this.sto);
this.dealerstoaddService.createDealerSto(this.username,this.sto).subscribe();
this.sto.address='';
this.sto.name='';

}

}
