import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from '../auth/token-storage.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent implements OnInit {

  private roles: String[];
  private authority: String;

  constructor(private tokenStorage: TokenStorageService) { }

  ngOnInit() {
    if (this.tokenStorage.getToken()) {
      this.roles = this.tokenStorage.getAuthorities();
      this.roles.every(role => {
        if (role === 'ROLE_CAR_OWNER') {
          this.authority = 'owner';
          return false;
        } else if (role === 'ROLE_SALES_MANAGER') {
          this.authority = 'sales';
          return false;
        } else if (role === 'ROLE_TECHNICAL_MANAGER') {
          this.authority = 'techmanager';
          return false;
        }else if (role === 'ROLE_DIELER') {
          this.authority = 'dieler';
          return false;
        }else if (role === 'ROLE_ADMIN') {
          this.authority = 'admin';
          return false;
        }
        this.authority = 'worker';
        return true;
      });
    }
  }

  openUserProfile(){}

  openHelp(){}

  logout(){}

  getAllUsers(){}

}
