import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {APP_ROUTING, ROUTING_COMPONENTS} from "./app.routing";
import {UserListComponent} from "./user/user-list/user-list.component";
import {HttpModule} from "@angular/http";


@NgModule({
  declarations: [
    AppComponent,
    ROUTING_COMPONENTS
    UserListComponent,
    ROUTING_COMPONENTS
  ],
  imports: [
    CommonModule,
    BrowserModule,
    APP_ROUTING,
    HttpModule,
    FormsModule,
    ReactiveFormsModule
    APP_ROUTING,
    HttpModule
  ],
  providers: [
    UserService
  ],
  bootstrap: [AppComponent],
  schemas: []
})
export class AppModule {
}
