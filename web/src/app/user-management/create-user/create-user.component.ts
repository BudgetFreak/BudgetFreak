import {Component} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {User} from "../../model/model-interfaces";
import {Router} from "@angular/router";
import {UserService} from "../user.service";

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html'
})
export class CreateUserComponent {

  userCreationForm: FormGroup;
  username: string = "";
  currency: string = "";
  usernameInputAlert: string = "Geben Sie einen Namen mit mindestens 4 Zeichen ein";
  currencyInputAlert: string = "Geben Sie ein Zeichen für die gewünschte Währung ein";

  constructor(private userService: UserService, private formBuilder: FormBuilder, private router: Router) {
  }

  ngOnInit() {
    this.userCreationForm = this.formBuilder.group({
      'usernameInput': [null, Validators.compose([Validators.required, Validators.minLength(4)])],
      'currencyInput': [null, Validators.compose([Validators.required, Validators.minLength(1), Validators.maxLength(1)])],
      'validate': ''
    });
  }

  createUser() {
    let newUser: User = {
      name: this.username,
      currency: this.currency
    };
    this.userService.create(newUser).subscribe();
    this.router.navigate(['/budgeting']);
  }

  addPost(post) {
    if (this.userCreationForm.valid) {
      this.username = post.usernameInput;
      this.currency = post.currencyInput;
      this.createUser();
    }
  }

}
