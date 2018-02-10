import {Component, OnInit} from '@angular/core';
import {User} from "../../model/model-interfaces";
import {UserService} from "../../user.service";

@Component({
  selector: 'app-select-user',
  templateUrl: './select-user.component.html'
})
export class SelectUserComponent implements OnInit {

  users: User[];

  constructor(private userService: UserService) {
  }

  ngOnInit() {
    this.userService.findAll().subscribe(users => {
      this.users = users
    });
  }

}
