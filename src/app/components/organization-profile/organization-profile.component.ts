import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {OrganizationService} from "../../_services/organization.service";
import {Organization} from "../../_models/organization";

@Component({
  selector: 'app-organization-profile',
  templateUrl: './organization-profile.component.html',
  providers: [OrganizationService],
  styleUrls: ['./organization-profile.component.css']
})
export class OrganizationProfileComponent implements OnInit {

  organization: Organization;
  private organizationUuid: string;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private organizationService: OrganizationService) { }

  ngOnInit() {
    this.organizationUuid = this.route.snapshot.paramMap.get('uuid');
    this.organizationService.getOrganization(this.organizationUuid).subscribe(
      (organization) => this.organization = organization,
      (error) => {
        this.router.navigateByUrl('');
        console.error(error)
      }
    )
  }

  openCreatorProfile() {
    this.router.navigateByUrl('/persons/' + this.organization.creatorUuid);
  }
}
