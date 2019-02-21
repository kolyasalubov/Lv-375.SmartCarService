import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from './auth/token-storage.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{
 
  constructor(private tokenStorage: TokenStorageService) { }

  isLogged(){
    let isLogged = this.tokenStorage.getToken();

    // TODO
    if(!isLogged 
        && window.location.href !== "http://localhost:4200/auth/login" 
        && window.location.href !== "http://localhost:4200/signup" ){
      console.log(window.location);
      window.location.href = "/auth/login";
    }
    return isLogged;
  }
 
  ngOnInit() {
  }
}
