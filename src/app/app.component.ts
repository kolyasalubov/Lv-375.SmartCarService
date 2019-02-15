import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from './auth/token-storage.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{

  private logged: boolean = true;
 
  constructor(private tokenStorage: TokenStorageService) { }
 
  ngOnInit() {
  }
}
