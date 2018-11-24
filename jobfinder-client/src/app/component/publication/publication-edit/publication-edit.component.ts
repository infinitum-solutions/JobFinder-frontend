import { Component, OnInit } from '@angular/core';
import {Publication} from "../../../model/publication";
import {PublicationService} from "../../../service/publication.service";
import {ActivatedRoute, Router} from "@angular/router";
import {FormControl} from "@angular/forms";

@Component({
  selector: 'app-publication-edit',
  templateUrl: './publication-edit.component.html',
  providers: [PublicationService],
  styleUrls: ['./publication-edit.component.css']
})
export class PublicationEditComponent implements OnInit {

  publication: Publication;

  publicationForm = new FormControl('');

  private link: string;

  constructor(
    private publicationService: PublicationService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit() {
    this.link = this.route.snapshot.paramMap.get('link');
    this.publicationService.getPublication(this.link).subscribe(
      (data) => this.publication = data,
      (error) => {
        this.router.navigate(['/404']);
        console.error(error)
      }
    )
  }

  debug() {
    console.log(this.publication);
    console.log(this.link)
  }

}
