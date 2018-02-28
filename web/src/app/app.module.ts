import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {APP_ROUTING, ROUTING_COMPONENTS} from "./app.routing";
import {HttpModule} from "@angular/http";
import {UserListComponent} from "./user/user-list/user-list.component";
import {UserService} from "./user-management/user.service";
import {LoginService} from "./login.service";

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
  providers: [
    UserService,
    LoginService
  ],
  bootstrap: [AppComponent],
  schemas: []
})
export class AppModule {
}
