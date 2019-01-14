import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';


import {AppComponent} from './app.component';
import {HttpClientModule} from "@angular/common/http";
import {FormsModule} from '@angular/forms';
import {routing} from "./app.routing";
import {PersonListComponent} from './component/person/person-list/person-list.component';
import {PersonProfileComponent} from './component/person/person-profile/person-profile.component';
import {PageNotFoundComponent} from './component/page-not-found/page-not-found.component';
import {WelcomeComponent} from './component/welcome/welcome.component';
import {LoginComponent} from './component/login/login.component';
import {PersonEditComponent} from './component/person/person-edit/person-edit.component';
import {OrganizationListComponent} from "./component/organization-list/organization-list.component";
import {PublicationListComponent} from "./component/publication-list/publication-list.component";
import {PublicationEditComponent} from "./component/publication-edit/publication-edit.component";
import {PublicationCreateComponent} from "./component/publication-create/publication-create.component";
import {OrganizationCreateComponent} from "./component/organization-create/organization-create.component";
import {OrganizationEditComponent} from "./component/organization-edit/organization-edit.component";
import { PublicationComponent } from './component/publication/publication.component';
import { OrganizationProfileComponent } from './component/organization-profile/organization-profile.component';

// import {HashLocationStrategy, LocationStrategy} from "@angular/common";


@NgModule({
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    routing,
  ],
  declarations: [
    AppComponent,
    WelcomeComponent,
    OrganizationListComponent,
    PersonListComponent,
    PublicationListComponent,
    PageNotFoundComponent,
    PersonProfileComponent,
    PublicationEditComponent,
    PublicationCreateComponent,
    LoginComponent,
    OrganizationCreateComponent,
    OrganizationEditComponent,
    PersonEditComponent,
    PublicationComponent,
    OrganizationProfileComponent
  ],
  // providers: [{provide: LocationStrategy, useClass: HashLocationStrategy}],
  bootstrap: [AppComponent]
})
export class AppModule {
}
