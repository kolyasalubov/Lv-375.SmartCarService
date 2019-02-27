import {Component, OnInit} from '@angular/core';
import {UserComponent} from '../user/user.component';
import {UsersService} from '../users/users.service';
import {User} from '../users/user';
import {TokenStorageService} from '../auth/token-storage.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {

  userProfile: User;
  username: String;

  constructor(private userService: UsersService, private tokenStorage: TokenStorageService) {
  }

  ngOnInit() {

    this.username = this.tokenStorage.getUsername();

    this.userService.getUserByUsername(this.username)
      .subscribe(data => this.userProfile = data);

    console.log(this.userProfile);
  }


}
