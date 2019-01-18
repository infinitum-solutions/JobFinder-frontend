import {Routes, RouterModule} from '@angular/router';
import {ModuleWithProviders} from "@angular/core";
import {PersonListComponent} from "./components/person-list/person-list.component";
import {PersonProfileComponent} from "./components/person-profile/person-profile.component";
import {WelcomeComponent} from "./components/welcome/welcome.component";
import {LoginComponent} from "./components/login/login.component";
import {OrganizationListComponent} from "./components/organization-list/organization-list.component";
import {PublicationListComponent} from "./components/publication-list/publication-list.component";
import {PublicationCreateComponent} from "./components/publication-create/publication-create.component";
import {PublicationEditComponent} from "./components/publication-edit/publication-edit.component";
import {OrganizationCreateComponent} from "./components/organization-create/organization-create.component";
import {PublicationComponent} from "./components/publication/publication.component";
import {OrganizationProfileComponent} from "./components/organization-profile/organization-profile.component";
import {AuthGuard} from "./_guards";

const appRoutes: Routes = [
  {path: '', component: WelcomeComponent},
  {path: 'login', component: LoginComponent},
  {path: 'organizations', component: OrganizationListComponent},
  {path: 'organizations/create', component: OrganizationCreateComponent, canActivate: [AuthGuard]},
  {path: 'organizations/:publicationUuid', component: OrganizationProfileComponent},
  {path: 'publications', component: PublicationListComponent},
  {path: 'publications/create', component: PublicationCreateComponent, canActivate: [AuthGuard]},
  {path: 'publications/:publicationUuid', component: PublicationComponent},
  {path: 'publications/:publicationUuid/edit', component: PublicationEditComponent, canActivate: [AuthGuard]},
  {path: 'persons', component: PersonListComponent},
  {path: 'persons/:uuid', component: PersonProfileComponent},
  {path: '**', redirectTo: ''}
];

export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);
