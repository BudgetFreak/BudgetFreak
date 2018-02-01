import {UserComponent} from "./user.component";
import {Routes} from "@angular/router";
import {BudgetingComponent} from "./budgeting/budgeting.component";
import {AccountsComponent} from "./accounts/accounts.component";

export const USER_ROUTES: Routes = [{
  path: 'user', component: UserComponent,
  children: [
    {path: 'budgeting', component: BudgetingComponent},
    {path: 'accounts', component: AccountsComponent}
  ]
}];

export const USER_ROUTING_COMPONENTS = [UserComponent, BudgetingComponent, AccountsComponent];
