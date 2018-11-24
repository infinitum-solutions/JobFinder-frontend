import {Component, Input, OnInit} from '@angular/core';
import {Organization} from "../../../../model/organization";

@Component({
  selector: 'app-organization',
  templateUrl: './organization-item.component.html',
  styleUrls: ['./organization-item.component.css']
})
export class OrganizationItemComponent implements OnInit {

  @Input() organization: Organization;

  constructor() { }

  ngOnInit() {
  }

}
