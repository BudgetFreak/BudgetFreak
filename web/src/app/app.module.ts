import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AppComponent} from './app.component';
import {APP_ROUTING, ROUTING_COMPONENTS} from "./app.routing";
import {UserService} from "./user-management/user.service";
import {LoginService} from "./login.service";


import {CommonModule, registerLocaleData} from "@angular/common";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import localeDe from '@angular/common/locales/de';
import localeDeExtra from '@angular/common/locales/extra/de';

registerLocaleData(localeDe, 'de-DE', localeDeExtra);

@NgModule({
  declarations: [
    AppComponent,
    ROUTING_COMPONENTS,
  ],
  imports: [
    CommonModule,
    BrowserModule,
    FormsModule,
    HttpClientModule,
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
