import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AppComponent} from './app.component';
import {APP_ROUTING, ROUTING_COMPONENTS} from "./app.routing";
import {UserListComponent} from "./user/user-list/user-list.component";
import {HttpModule} from "@angular/http";
import {CommonModule} from "@angular/common";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {UserService} from "./user.service";


@NgModule({
  declarations: [
    AppComponent,
    UserListComponent,
    ROUTING_COMPONENTS
  ],
  imports: [
    CommonModule,
    BrowserModule,
    FormsModule,
    HttpModule,
    ReactiveFormsModule,
    APP_ROUTING
  ],
  providers: [
    UserService
  ],
  bootstrap: [AppComponent],
  schemas: []
})
export class AppModule {
}
