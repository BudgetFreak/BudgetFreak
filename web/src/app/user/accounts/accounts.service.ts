import {Injectable} from '@angular/core';
import {User} from "../../model/model-interfaces";
import {Http} from "@angular/http";
import {Observable} from "rxjs/Observable";

@Injectable()
export class AccountsService {

  constructor(private http: Http) {
  }

  getAccounts(user: User) {

  }
}
