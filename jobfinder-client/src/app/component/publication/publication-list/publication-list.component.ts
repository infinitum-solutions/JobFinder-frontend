import {Component, OnInit} from '@angular/core';
import {Publication} from "../../../model/publication";
import {PublicationService} from "../../../service/publication.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-publication-list',
  providers: [PublicationService],
  templateUrl: './publication-list.component.html',
  styleUrls: ['./publication-list.component.css']
})
export class PublicationListComponent implements OnInit {

  publications: Publication[];

  constructor(
    private publicationService: PublicationService,
    private router: Router
  ) {
  }

  ngOnInit() {
    this.publicationService.getPublications().subscribe(
      (data) => this.publications = data,
      (error) => console.error(error)
    );
  }

  createPublication() {
    this.router.navigate(['/publications/create']);
  }

}
