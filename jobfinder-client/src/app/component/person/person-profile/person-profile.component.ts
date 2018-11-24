import {Component, OnInit} from '@angular/core';
import {Person} from "../../../model/person";
import {ActivatedRoute, Router} from "@angular/router";
import {PersonService} from "../../../service/person.service";

@Component({
  selector: 'app-person-profile',
  templateUrl: './person-profile.component.html',
  providers: [PersonService],
  styleUrls: ['./person-profile.component.css']
})
export class PersonProfileComponent implements OnInit {

  person: Person;
  link: string;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private personService: PersonService) {
  }

  ngOnInit() {
    this.link = this.route.snapshot.paramMap.get('link');
    this.personService.getPerson(this.link).subscribe(
      (data) => this.person = data,
      (error) => {
        this.router.navigate(['/404']);
        console.error("ЗАЕБАЛ")
      }
    )
  }

}
