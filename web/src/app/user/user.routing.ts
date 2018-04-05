import {UserComponent} from "./user.component";
import {Routes} from "@angular/router";
import {AccountsComponent} from "./accounts/accounts.component";
import {BudgetingComponent} from "./budgeting/budgeting.component";
import {BudgetingOverviewHeaderComponent} from "./budgeting/budgeting-overview-header/budgeting-overview-header.component";
import {BudgetingMonthNavComponent} from "./budgeting/budgeting-month-nav/budgeting-month-nav.component";
import {BudgetHeadersComponent} from "./budgeting/budget-headers/budget-headers.component";
import {BudgetGroupComponent} from "./budgeting/budget-group/budget-group.component";
import {BudgetCategoryComponent} from "./budgeting/budget-group/budget-category/budget-category.component";

export const USER_ROUTES: Routes = [{
  path: '', component: UserComponent,
  children: [
    {path: 'accounts', component: AccountsComponent},
    {path: 'budgeting', component: BudgetingComponent}
  ]
}];

export const USER_ROUTING_COMPONENTS = [
  UserComponent,
  BudgetingComponent,
  AccountsComponent,
  BudgetingOverviewHeaderComponent,
  BudgetingMonthNavComponent,
  BudgetHeadersComponent,
  BudgetGroupComponent,
  BudgetCategoryComponent
];
