import {Component, Input, OnInit} from '@angular/core';
import {Person} from "../../../../model/person";
import {Router} from "@angular/router";

@Component({
  selector: 'app-person',
  templateUrl: './person-item.component.html',
  styleUrls: ['./person-item.component.css']
})
export class PersonItemComponent implements OnInit {

  @Input() person: Person;

  constructor(
    private router: Router
  ) {
  }

  ngOnInit() {
  }

  onButtonClick() {
    this.router.navigate(['/user', this.person.link])
  }
}


