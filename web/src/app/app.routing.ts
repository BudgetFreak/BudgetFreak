import {RouterModule, Routes} from "@angular/router";
import {LoginComponent} from "./login/login.component";
import {BudgetingComponent} from "./user/budgeting/budgeting.component";
import {AccountsComponent} from "./user/accounts/accounts.component";
import {UserComponent} from "./user/user.component";

export const APP_ROUTES: Routes = [
  {path: '', component: LoginComponent},
  {path: 'user', component: UserComponent},
  {path: 'user/budgeting', component: BudgetingComponent},
  {path: 'user/accounts', component: AccountsComponent}
];

export const APP_ROUTING = RouterModule.forRoot(APP_ROUTES);
export const ROUTING_COMPONENTS = [LoginComponent, BudgetingComponent, UserComponent, AccountsComponent];
