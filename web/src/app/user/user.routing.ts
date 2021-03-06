import {UserComponent} from "./user.component";
import {Routes} from "@angular/router";
import {AccountsComponent} from "./accounts/accounts.component";
import {BudgetingComponent} from "./budgeting/budgeting.component";

export const USER_ROUTES: Routes = [{
  path: '', component: UserComponent,
  children: [
    {path: 'accounts', component: AccountsComponent},
    {path: 'budget', component: BudgetingComponent}
  ]
}];

export const USER_ROUTING_COMPONENTS = [UserComponent, BudgetingComponent, AccountsComponent];
