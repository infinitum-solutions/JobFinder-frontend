import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';


import {AppComponent} from './app.component';
import {HttpClientModule} from "@angular/common/http";
import {FormsModule} from '@angular/forms';
import {routing} from "./app.routing";
import {OrganizationListComponent} from './component/organization/organization-list/organization-list.component';
import {OrganizationItemComponent} from './component/organization/organization-list/organization-item/organization-item.component';
import {PersonListComponent} from './component/person/person-list/person-list.component';
import {PersonItemComponent} from './component/person/person-list/person-item/person-item.component';
import {PersonProfileComponent} from './component/person/person-profile/person-profile.component';
import {PageNotFoundComponent} from './component/page-not-found/page-not-found.component';
import {WelcomeComponent} from './component/welcome/welcome.component';
import {PublicationEditComponent} from './component/publication/publication-edit/publication-edit.component';
import {PublicationCreateComponent} from './component/publication/publication-create/publication-create.component';
import {PublicationListComponent} from './component/publication/publication-list/publication-list.component';
import {PublicationItemComponent} from './component/publication/publication-list/publication-item/publication-item.component';
import {LoginComponent} from './component/login/login.component';
import {OrganizationCreateComponent} from './component/organization/organization-create/organization-create.component';
import {OrganizationEditComponent} from './component/organization/organization-edit/organization-edit.component';
import {PersonEditComponent} from './component/person/person-edit/person-edit.component';

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
    PersonItemComponent,
    PersonProfileComponent,
    PublicationEditComponent,
    PublicationCreateComponent,
    PublicationItemComponent,
    OrganizationItemComponent,
    LoginComponent,
    OrganizationCreateComponent,
    OrganizationEditComponent,
    PersonEditComponent
  ],
  // providers: [{provide: LocationStrategy, useClass: HashLocationStrategy}],
  bootstrap: [AppComponent]
})
export class AppModule {
}
