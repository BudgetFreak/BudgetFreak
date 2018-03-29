import {Injectable} from '@angular/core';
import {User} from "./model/model-interfaces";

@Injectable()
export class LoginService {

  private currentUser: User;

  constructor() {
  }

  getCurrentUser(): User {
    return this.currentUser;
  }

  setCurrentUser(user: User) {
    this.currentUser = user;
  }

}
