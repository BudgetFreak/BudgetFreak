import { Component, OnInit } from '@angular/core';
import {User} from "../../model/model-interfaces";
import {UserService} from "../../user.service";

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html'
})
export class CreateUserComponent  {

  textValue = "asfd";

  constructor(private userService: UserService) { }

  createUser(){
    let user : User = new User();
    user.name = "safd";
    this.userService.create(user);
    console.log(user.name);
  }
}
