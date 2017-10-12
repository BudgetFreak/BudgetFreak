import {RouterModule, Routes} from "@angular/router";
import {LoginComponent} from "./login/login.component";
import {USER_ROUTES, USER_ROUTING_COMPONENTS} from "./user/user.routing";

export const APP_ROUTES: Routes = [
  {path: '', component: LoginComponent},
  ...USER_ROUTES
];

export const APP_ROUTING = RouterModule.forRoot(APP_ROUTES);
export const ROUTING_COMPONENTS = [LoginComponent, ...USER_ROUTING_COMPONENTS];
