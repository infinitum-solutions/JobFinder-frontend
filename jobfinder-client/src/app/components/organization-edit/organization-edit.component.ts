import {Component, OnInit} from '@angular/core';
import {Organization} from "../../_models/organization";

@Component({
  selector: 'app-organization-edit',
  templateUrl: './organization-edit.component.html',
  styleUrls: ['./organization-edit.component.css']
})
export class OrganizationEditComponent implements OnInit {

  organization: Organization;

  submitted = false;

  constructor() {
  }

  ngOnInit() {
  }

  onSubmit() {
    this.submitted = true;
  }

}
