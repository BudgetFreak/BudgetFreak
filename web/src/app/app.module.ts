import {BrowserModule} from '@angular/platform-browser';
import {ClarityModule} from 'clarity-angular';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {UserComponent} from './user/user.component';
import {APP_ROUTING, ROUTING_COMPONENTS} from "./app.routing";

@NgModule({
  declarations: [
    AppComponent,
    UserComponent,
    ROUTING_COMPONENTS
  ],
  imports: [
    BrowserModule,
    ClarityModule.forRoot(),
    APP_ROUTING
  ],
  providers: [],
  bootstrap: [AppComponent],
  schemas: []
})
export class AppModule {
}
