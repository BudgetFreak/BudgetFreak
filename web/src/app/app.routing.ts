import {RouterModule, Routes} from "@angular/router";
import {USER_ROUTES, USER_ROUTING_COMPONENTS} from "./user/user.routing";
import {UserManagementComponent} from "./user-management/user-management.component";
import {USER_MANAGEMENT_ROUTES, USER_MANAGEMENT_ROUTING_COMPONENTS} from "./user-management/user-management.routes";

export const APP_ROUTES: Routes = [
  {path: '', redirectTo: '/login', pathMatch: 'full'},
  ...USER_MANAGEMENT_ROUTES,
  ...USER_ROUTES
];

export const APP_ROUTING = RouterModule.forRoot(
  APP_ROUTES
);
export const ROUTING_COMPONENTS = [...USER_ROUTING_COMPONENTS, ...USER_MANAGEMENT_ROUTING_COMPONENTS];
