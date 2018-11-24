import {Component, Inject, OnInit} from '@angular/core';
import {Person} from "../../../model/person";
import {PersonService} from "../../../service/person.service";

@Component({
  selector: 'app-person-list',
  providers: [PersonService],
  templateUrl: './person-list.component.html',
  styleUrls: ['./person-list.component.css']
})
export class PersonListComponent implements OnInit {

  persons: Person[];
  selectedPerson: Person;

  constructor(private personService: PersonService) {
  }

  ngOnInit() {
    this.personService.getPersons().subscribe(
      (data) => this.persons = data,
      (error) => console.error(error)
    )
  }

  onSelect(person: Person): void {
    this.selectedPerson = person;
  }

}
