import {Routes, RouterModule} from '@angular/router';
import {ModuleWithProviders} from "@angular/core";
import {PersonListComponent} from "./component/person/person-list/person-list.component";
import {PersonProfileComponent} from "./component/person/person-profile/person-profile.component";
import {WelcomeComponent} from "./component/welcome/welcome.component";
import {LoginComponent} from "./component/login/login.component";
import {OrganizationListComponent} from "./component/organization-list/organization-list.component";
import {PublicationListComponent} from "./component/publication-list/publication-list.component";
import {PublicationCreateComponent} from "./component/publication-create/publication-create.component";
import {PublicationEditComponent} from "./component/publication-edit/publication-edit.component";
import {OrganizationCreateComponent} from "./component/organization-create/organization-create.component";
import {PublicationComponent} from "./component/publication/publication.component";
import {OrganizationProfileComponent} from "./component/organization-profile/organization-profile.component";

const appRoutes: Routes = [
  {path: '', component: WelcomeComponent},
  {path: 'login', component: LoginComponent},
  {path: 'organizations', component: OrganizationListComponent},
  {path: 'organizations/create', component: OrganizationCreateComponent},
  {path: 'organizations/:publicationUuid', component: OrganizationProfileComponent},
  {path: 'publications', component: PublicationListComponent},
  {path: 'publications/create', component: PublicationCreateComponent},
  {path: 'publications/:publicationUuid', component: PublicationComponent},
  {path: 'publications/:publicationUuid/edit', component: PublicationEditComponent},
  {path: 'persons', component: PersonListComponent},
  {path: 'persons/:uuid', component: PersonProfileComponent},
  {path: '**', redirectTo: ''}
];

export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);
