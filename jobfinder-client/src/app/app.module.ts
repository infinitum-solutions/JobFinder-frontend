import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';


import {AppComponent} from './app.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {routing} from "./app.routing";
import {PersonListComponent} from './components/person-list/person-list.component';
import {PersonProfileComponent} from './components/person-profile/person-profile.component';
import {PageNotFoundComponent} from './components/page-not-found/page-not-found.component';
import {WelcomeComponent} from './components/welcome/welcome.component';
import {LoginComponent} from './components/login/login.component';
import {PersonEditComponent} from './components/person-edit/person-edit.component';
import {OrganizationListComponent} from "./components/organization-list/organization-list.component";
import {PublicationListComponent} from "./components/publication-list/publication-list.component";
import {PublicationEditComponent} from "./components/publication-edit/publication-edit.component";
import {PublicationCreateComponent} from "./components/publication-create/publication-create.component";
import {OrganizationCreateComponent} from "./components/organization-create/organization-create.component";
import {OrganizationEditComponent} from "./components/organization-edit/organization-edit.component";
import {PublicationComponent} from './components/publication/publication.component';
import {OrganizationProfileComponent} from './components/organization-profile/organization-profile.component';
import {BasicAuthInterceptor, ErrorInterceptor} from "./_helpers";

// import {HashLocationStrategy, LocationStrategy} from "@angular/common";


@NgModule({
  imports: [
    BrowserModule,
    ReactiveFormsModule,
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
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: BasicAuthInterceptor, multi: true},
    {provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true},
    // {provide: LocationStrategy, useClass: HashLocationStrategy}
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
