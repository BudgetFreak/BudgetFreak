import {UserComponent} from "./user.component";
import {Routes} from "@angular/router";
import {AccountsComponent} from "./accounts/accounts.component";
import {BudgetingComponent} from "./budgeting/budgeting.component";
import {BudgetingBannerComponent} from "./budgeting/budgeting-banner/budgeting-banner.component";
import {MonthNavComponent} from "./budgeting/month-nav/month-nav.component";
import {BudgetHeadersComponent} from "./budgeting/budget-headers/budget-headers.component";
import {BudgetGroupsComponent} from "./budgeting/budget-groups/budget-groups.component";
import {BudgetGroupComponent} from "./budgeting/budget-groups/budget-group/budget-group.component";
import {BudgetEntriesComponent} from "./budgeting/budget-groups/budget-group/budget-entries/budget-entries.component";
import {BudgetEntryComponent} from "./budgeting/budget-groups/budget-group/budget-entries/budget-entry/budget-entry.component";

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
  BudgetingBannerComponent,
  MonthNavComponent,
  BudgetHeadersComponent,
  BudgetGroupsComponent,
  BudgetGroupComponent,
  BudgetEntriesComponent,
  BudgetEntryComponent
];
