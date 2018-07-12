import { UserComponent } from "./user.component";
import { Routes } from "@angular/router";
import { AccountsComponent } from "./accounts/accounts.component";
import { BudgetingComponent } from "./budgeting/budgeting.component";
import { BudgetingBannerComponent } from "./budgeting/budgeting-banner/budgeting-banner.component";
import { MonthNavComponent } from "./budgeting/month-nav/month-nav.component";
import { MasterCategoriesComponent } from "./budgeting/master-categories/master-categories.component";
import { MasterCategoryComponent } from "./budgeting/master-categories/master-category/master-category.component";
import { CategoryComponent } from "./budgeting/master-categories/master-category/category/category.component";

export const USER_ROUTES: Routes = [{
  path: '', component: UserComponent,
  children: [
    { path: 'accounts', component: AccountsComponent },
    { path: 'budgeting', component: BudgetingComponent }
  ]
}];

export const USER_ROUTING_COMPONENTS = [
  UserComponent,
  BudgetingComponent,
  AccountsComponent,
  BudgetingBannerComponent,
  MonthNavComponent,
  MasterCategoriesComponent,
  MasterCategoryComponent,
  CategoryComponent
];
