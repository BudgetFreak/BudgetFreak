import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AppComponent} from './app.component';
import {APP_ROUTING, ROUTING_COMPONENTS} from "./app.routing";
import {HttpModule} from "@angular/http";
import {UserService} from "./user-management/user.service";
import {LoginService} from "./login.service";
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import {CommonModule} from "@angular/common";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";

@NgModule({
  declarations: [
    AppComponent,
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
    UserService,
    LoginService
  ],
  bootstrap: [AppComponent],
  schemas: []
})
export class AppModule {
}
