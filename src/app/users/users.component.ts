import { Component, OnInit } from '@angular/core';
import { UsersService } from './users.service';
import { User } from './user';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss']
})
export class UsersComponent implements OnInit {

  users: User[];

  constructor(private usersService: UsersService) { }

ngOnInit() {
this.usersService.getAll()
.subscribe (data => this.users = data);
  console.log(this.users);
}

  

  deleteUserById(id: number){
    this.usersService.deleteUserById(id)
  }

}
