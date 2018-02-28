import {Component, OnInit} from '@angular/core';
import {LoginService} from "../../login.service";
import {AccountsService} from "./accounts.service";
import {User} from "../../model/model-interfaces";

@Component({
  selector: 'app-accounts',
  templateUrl: './accounts.component.html'
})
export class AccountsComponent implements OnInit {

  constructor(private accountsService: AccountsService,
              private loginService: LoginService) {
  }

  ngOnInit() {
    const currentUser: User = this.loginService.getCurrentUser();
    this.accountsService.getAccounts(currentUser);

  }

}
