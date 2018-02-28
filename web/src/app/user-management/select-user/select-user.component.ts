import {Component, OnInit} from '@angular/core';
import {User} from "../../model/model-interfaces";
import {UserService} from "../user.service";
import {LoginService} from "../../login.service";

@Component({
  selector: 'app-select-user',
  templateUrl: './select-user.component.html'
})
export class SelectUserComponent implements OnInit {

  users: User[];

  constructor(private userService: UserService,
              private loginService: LoginService) {
  }

  ngOnInit() {
    this.userService.findAll().subscribe(users => {
      this.users = users
    });
  }

  onUserSelect(user: User) {
    this.loginService.setCurrentUser(user);
  }
}
