import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-select-user',
  templateUrl: './select-user.component.html'
})
export class SelectUserComponent implements OnInit {

  users: User[];

  constructor(private userService: UserService,
              private loginService: LoginService,
              private router: Router) {
  }

  ngOnInit() {
    this.userService.findAll().subscribe(users => {
      this.users = users
    });
  }

  onUserSelect(user: User) {
    this.loginService.setCurrentUser(user);
    this.router.navigate(['/budgeting']);
  }
}
