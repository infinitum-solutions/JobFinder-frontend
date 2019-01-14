import {Component, OnInit} from '@angular/core';
import {OrganizationService} from "../../service/organization.service";
import {Organization} from "../../model/organization";
import {Router} from "@angular/router";

@Component({
  selector: 'app-organization-list',
  providers: [OrganizationService],
  templateUrl: './organization-list.component.html',
  styleUrls: ['./organization-list.component.css']
})
export class OrganizationListComponent implements OnInit {

  organizations: Organization[];

  constructor(private organizationsService: OrganizationService,
              private router: Router) {
  }

  ngOnInit() {
    this.organizationsService.getOrganizations().subscribe(
      (data) => this.organizations = data,
      (error) => console.error(error)
    )
  }

  createOrganization() {
    this.router.navigateByUrl('/organizations/create');
  }

  openOrganization(organization: Organization) {
    alert(organization.uuid);
    this.router.navigateByUrl('/organizations/' + organization.uuid)
  }
}
