import {Routes} from "@angular/router";
import {UserManagementComponent} from "./user-management.component";
import {CreateUserComponent} from "./create-user/create-user.component";
import {SelectUserComponent} from "./select-user/select-user.component";

export const USER_MANAGEMENT_ROUTES: Routes = [{
  path: 'login', component: UserManagementComponent,
  children: [
    {path: '', component: SelectUserComponent},
    {path: 'create', component: CreateUserComponent}
  ]
}];

export const USER_MANAGEMENT_ROUTING_COMPONENTS = [UserManagementComponent, CreateUserComponent, SelectUserComponent];
