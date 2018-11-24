import { Component, OnInit } from '@angular/core';
import {Organization} from "../../../model/organization";
import {OrganizationService} from "../../../service/organization.service";

@Component({
  selector: 'app-organization-list',
  providers: [OrganizationService],
  templateUrl: './organization-list.component.html',
  styleUrls: ['./organization-list.component.css']
})
export class OrganizationListComponent implements OnInit {

  organizations: Organization[];

  constructor(private organizationsService: OrganizationService) { }

  ngOnInit() {
    this.organizationsService.getOrganizations().subscribe(
      (data) => this.organizations = data,
      (error) => console.error(error)
    )
  }

  createOrganization() {
    this.organizationsService.createOrganization(null).subscribe();
  }
}
