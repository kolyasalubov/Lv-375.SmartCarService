import { Component, OnInit } from '@angular/core';
import { UserComponent } from '../user/user.component';
import { UsersService } from '../users/users.service';
import { User } from '../users/user';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {

  userProfile: User;

  constructor(private userService: UsersService) { }

  ngOnInit() {
this.userService.getUserById(2)
.subscribe (data => this.userProfile = data);

  console.log(this.userProfile);
  }


}
