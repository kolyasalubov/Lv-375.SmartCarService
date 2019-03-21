import { Component, OnInit,Input } from '@angular/core';
import { TokenStorageService } from 'src/app/auth/token-storage.service';
import { ActivatedRoute, Router } from '@angular/router';
import{CarsService} from 'src/app/cars/cars.service';
import {Car } from 'src/app/cars/car';
import{Dealer} from 'src/app/dealer/dealer';
import { from, Observable } from 'rxjs';
import { TradeInService } from './trade-in.service';
import {TradeIn} from './tradein';
import {Globals} from "../globals";
import {FormGroup, FormControl, Validators } from "@angular/forms";
import {Message} from "./message";
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import { ToastrService } from 'ngx-toastr';
import { FormsModule } from '@angular/forms';



@Component({
  selector: 'app-trade-in',
  templateUrl: './trade-in.component.html',
  styleUrls: ['./trade-in.component.scss']

})
export class TradeInComponent implements OnInit{
  private serverUrl=Globals.baseURL + "/chat";
     username: String;
    mycar:Car;
    dealers: Dealer[];
    allCars:Car[];
  @Input()
  vin: String;
  private stompClient;
  messages: Message[] = [];
  isCustomSocketOpened = true;

  fromId:string;
  toId:string;
  msg:string;

  constructor(private toastr: ToastrService,private tradeInService:TradeInService,private carsService:CarsService,private tokenStorage: TokenStorageService,private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {
this.fromId=this.tokenStorage.getUsername();

              this.route.params.subscribe(params=>{
              this.vin=params["vin"];
            })

             this.carsService.getCarByVin(this.vin).subscribe(data=>this.mycar=data);

             this.tradeInService.getAllCars().subscribe(data=>this.allCars=data);

             this.tradeInService.getAllDealers().subscribe(data=>this.dealers=data);

             this.username = this.tokenStorage.getUsername();

    this.initializeWebSocketConnection();

    this.openSocket();

    }
  sendMessageUsingRest() {
      let message: Message = { message: this.msg, fromId: this.fromId, toId: this.toId };
      this.tradeInService.post(message).subscribe(res => {
        console.log(res);
      })

  }

  initializeWebSocketConnection() {
    let ws = new SockJS(this.serverUrl);
    this.stompClient = Stomp.over(ws);
    let that = this;
    this.stompClient.connect({}, function (frame) {
      that.openGlobalSocket()
    });
  }
  openGlobalSocket() {
    this.stompClient.subscribe("/socket-publisher", (message) => {
      this.handleResult(message);
    });
  }

  openSocket() {
        this.stompClient.subscribe("/socket-publisher/"+this.tokenStorage.getUsername(), (message) => {
        this.handleResult(message);
      });
  }

  handleResult(message){
    if (message.body) {
      let messageResult: Message = JSON.parse(message.body);
      console.log(messageResult);
      this.messages.push(messageResult);
      this.toastr.success("new message recieved", null, {
        'timeOut': 3000
      });
    }
  }



  private getFromRouterParams(param: string) {
    return this.route.snapshot.queryParamMap.get(param);
  }

  private getCars(edr:String){
    return this.tradeInService.getAllCarsByDealerEdr(edr).subscribe();
  }

  sendTradeIn(newCarvin: String,UsedCarvin: String) {
    return this.tradeInService.sendTradeIn(newCarvin,UsedCarvin).subscribe();
  }




}
