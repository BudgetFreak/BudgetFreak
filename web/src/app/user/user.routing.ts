import {UserComponent} from "./user.component";
import {Routes} from "@angular/router";
import {AccountsComponent} from "./accounts/accounts.component";
import {BudgetingComponent} from "./budgeting/budgeting.component";
import {UserListComponent} from "./user-list/user-list.component";

export const USER_ROUTES: Routes = [{
  path: 'user', component: UserComponent,
  children: [
    {path: 'accounts', component: AccountsComponent},
    {path: 'budgeting', component: BudgetingComponent},
    {path: 'user-list', component: UserListComponent}
  ]
}];

export const USER_ROUTING_COMPONENTS = [UserComponent, BudgetingComponent, AccountsComponent, UserListComponent];
