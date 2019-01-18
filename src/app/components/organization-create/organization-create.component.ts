import {Component, OnInit, ViewChild} from '@angular/core';
import {NgForm} from "@angular/forms";
import {Router} from "@angular/router";
import {OrganizationService} from "../../_services/organization.service";

@Component({
  selector: 'app-organization-create',
  templateUrl: './organization-create.component.html',
  providers: [OrganizationService],
  styleUrls: ['./organization-create.component.css']
})
export class OrganizationCreateComponent implements OnInit {

  @ViewChild("organization") private organizationForm: NgForm;

  constructor(private organizationService: OrganizationService,
              private router: Router) {
  }

  ngOnInit() {
  }

  onSubmit() {
    this.organizationService.createOrganization(this.organizationForm.value).subscribe(
      (organization) => {
        this.router.navigateByUrl('/organizations/' + organization.uuid);
      },
      (error) => console.error(error)
    );
  }

}
