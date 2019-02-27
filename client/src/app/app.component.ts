import {Component, OnInit} from '@angular/core';
import {TokenStorageService} from './auth/token-storage.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  constructor(private tokenStorage: TokenStorageService) {
  }

  isLogged() {
    let isLogged = this.tokenStorage.getToken();

    // TODO
    if (!isLogged
      && window.location.href !== "http://localhost:9501/ui/auth/login"
      && window.location.href !== "http://localhost:9501/ui/signup"
      && !window.location.href.includes('/ui/auth/login')) {
      console.log(window.location);
      window.location.href = "/ui/auth/login";
    }
    return isLogged;
  }

  ngOnInit() {
  }
}
