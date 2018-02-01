import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {APP_ROUTING, ROUTING_COMPONENTS} from "./app.routing";
import {HttpModule} from "@angular/http";
import {UserListComponent} from "./user/user-list/user-list.component";
import {UserManagementComponent} from "./user-management/user-management.component";
import { CreateUserComponent } from './user-management/create-user/create-user.component';
import { SelectUserComponent } from './user-management/select-user/select-user.component';

@NgModule({
  declarations: [
    AppComponent,
    UserListComponent,
    ROUTING_COMPONENTS
  ],
  imports: [
    BrowserModule,
    APP_ROUTING,
    HttpModule
  ],
  providers: [],
  bootstrap: [AppComponent],
  schemas: []
})
export class AppModule {
}
