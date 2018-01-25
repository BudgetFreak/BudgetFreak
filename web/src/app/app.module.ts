import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {UserComponent} from './user/user.component';
import {APP_ROUTING, ROUTING_COMPONENTS} from "./app.routing";
import {HttpModule} from "@angular/http";

@NgModule({
  declarations: [
    AppComponent,
    UserComponent,
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
