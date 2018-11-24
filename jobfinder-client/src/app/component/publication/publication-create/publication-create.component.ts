import { Component, OnInit } from '@angular/core';
import {Publication} from "../../../model/publication";
import {FormGroup} from "@angular/forms";
import {PublicationService} from "../../../service/publication.service";

@Component({
  selector: 'app-publication-create',
  templateUrl: './publication-create.component.html',
  providers: [PublicationService],
  styleUrls: ['./publication-create.component.css']
})
export class PublicationCreateComponent implements OnInit {

  form: FormGroup;
  publication: Publication;
  submited: boolean = false;

  constructor(
    private publicationService: PublicationService,
  ) { }

  ngOnInit() {
  }

  onSubmit() {
    this.publicationService.createPublication(this.form.value).subscribe(
      (data) => {},
      (error) => console.error(error)
    );
    this.submited = true;
  }

}
