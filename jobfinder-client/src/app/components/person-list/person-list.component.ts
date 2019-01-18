import {Component, OnInit} from '@angular/core';
import {Person} from "../../_models";
import {PersonService} from "../../_services";
import {Router} from "@angular/router";

@Component({
  selector: 'app-person-list',
  providers: [PersonService],
  templateUrl: './person-list.component.html',
  styleUrls: ['./person-list.component.css']
})
export class PersonListComponent implements OnInit {

  persons: Person[];

  constructor(private personService: PersonService,
              private router: Router) {
  }

  ngOnInit() {
    this.personService.getPersons().subscribe(
      (data) => this.persons = data,
      (error) => console.error(error)
    )
  }

  openProfile(person: Person) {
    this.router.navigateByUrl('/persons/' + person.uuid)
  }
}
