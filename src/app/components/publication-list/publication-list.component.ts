import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {PublicationService} from "../../_services/publication.service";
import {Publication} from "../../_models/publication";

@Component({
  selector: 'app-publication-list',
  providers: [PublicationService],
  templateUrl: './publication-list.component.html',
  styleUrls: ['./publication-list.component.css']
})
export class PublicationListComponent implements OnInit {

  publications: Publication[];

  constructor(private publicationService: PublicationService,
              private router: Router) {
  }

  ngOnInit() {
    this.publicationService.getPublications().subscribe(
      (data) => this.publications = data,
      (error) => console.error(error)
    );
  }

  createPublication() {
    this.router.navigateByUrl('/publications/create');
  }

  openPublication(publication: Publication) {
    this.router.navigateByUrl('/publications/' + publication.uuid)
  }

}
