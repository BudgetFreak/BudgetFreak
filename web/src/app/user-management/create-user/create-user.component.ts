import {Component} from '@angular/core';
import {UserService} from "../../user.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {User} from "../../model/model-interfaces";

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html'
})
export class CreateUserComponent {

  userCreationForm: FormGroup;
  formSubmited: boolean = false;
  username: string = "";
  currency: string = "";
  usernameInputAlert: string = "Geben Sie einen Namen mit mindestens 4 Zeichen ein";
  currencyInputAlert: string = "Geben Sie ein Zeichen für die gewünschte Währung ein";

  constructor(private userService: UserService, private formBuilder: FormBuilder) {
  }

  ngOnInit() {
    this.userCreationForm = this.formBuilder.group({
      'usernameInput': [null, Validators.compose([Validators.required, Validators.minLength(4)])],
      'currencyInput': [null, Validators.compose([Validators.required, Validators.minLength(1), Validators.maxLength(1)])],
      'validate': ''
    });
    this.formSubmited = false;
  }

  createUser() {
    let newUser: User = {
      name: this.username,
      currency: this.currency
    };
    this.userService.create(newUser).subscribe();
    this.formSubmited = true;
  }

  addPost(post) {
    if (this.userCreationForm.valid) {
      this.username = post.usernameInput;
      this.currency = post.currencyInput;
      this.createUser();
    }
  }

}
