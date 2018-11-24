import {Routes, RouterModule} from '@angular/router';
import {ModuleWithProviders} from "@angular/core";
import {OrganizationListComponent} from "./component/organization/organization-list/organization-list.component";
import {PersonListComponent} from "./component/person/person-list/person-list.component";
import {PersonProfileComponent} from "./component/person/person-profile/person-profile.component";
import {PublicationListComponent} from "./component/publication/publication-list/publication-list.component";
import {PublicationCreateComponent} from "./component/publication/publication-create/publication-create.component";
import {PageNotFoundComponent} from "./component/page-not-found/page-not-found.component";
import {WelcomeComponent} from "./component/welcome/welcome.component";
import {LoginComponent} from "./component/login/login.component";
import {PublicationEditComponent} from "./component/publication/publication-edit/publication-edit.component";

const appRoutes: Routes = [
  {path: '', component: WelcomeComponent},
  {path: 'login', component: LoginComponent},
  {path: 'user/:link', component: PersonProfileComponent},
  {path: 'organizations', component: OrganizationListComponent},
  {path: 'persons', component: PersonListComponent},
  {path: 'publications', component: PublicationListComponent},
  {path: 'publications/create', component: PublicationCreateComponent},
  {path: 'publications/:link/edit', component: PublicationEditComponent},
  {path: '404', component: PageNotFoundComponent},
  {path: '**', component: PageNotFoundComponent}
];

export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);
