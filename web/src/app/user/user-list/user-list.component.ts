import {Component, OnInit} from '@angular/core';
import {UserService} from "../user.service";
import {User} from "../../model/model-interfaces";

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  providers: [UserService]
})
export class UserListComponent implements OnInit {

  private users: User[];

  constructor(private userService: UserService) {

  }

  getAllUsers() {
    this.userService.findAll().subscribe(
      users => {
        this.users = users;
        console.dir(users);
      },
      err => {
        console.log(err);
      }
    );
  }

  ngOnInit(): void {
    this.getAllUsers();
  }

}

