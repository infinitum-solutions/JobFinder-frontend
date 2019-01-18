import {Component, OnInit} from '@angular/core';
import {Person} from "../../_models";
import {ActivatedRoute, Router} from "@angular/router";
import {PersonService} from "../../_services";
import {Publication} from "../../_models";

@Component({
  selector: 'app-person-profile',
  templateUrl: './person-profile.component.html',
  providers: [PersonService],
  styleUrls: ['./person-profile.component.css']
})
export class PersonProfileComponent implements OnInit {

  person: Person;
  publications: Publication[];
  hasPublications: boolean;
  private personUuid: string;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private personService: PersonService) {
  }

  ngOnInit() {
    this.personUuid = this.route.snapshot.paramMap.get('uuid');
    this.personService.getPerson(this.personUuid).subscribe(
      (data) => this.person = data,
      (error) => {
        this.router.navigateByUrl('');
        console.error(error)
      }
    );
    this.personService.getPersonPublications(this.personUuid).subscribe(
      (arr) => {
        if (arr.length == 0) {
          this.hasPublications = false;
        } else {
          this.publications = arr;
          this.hasPublications = true;
        }
      },
      (error) => alert(error)
    )
  }

}
