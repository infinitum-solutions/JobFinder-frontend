import {Component, OnInit} from '@angular/core';
import {Publication} from "../../model/publication";
import {PublicationService} from "../../service/publication.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-publication',
  templateUrl: './publication.component.html',
  providers: [PublicationService],
  styleUrls: ['./publication.component.css']
})
export class PublicationComponent implements OnInit {

  publication: Publication;
  private publicationUuid: string;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private publicationService: PublicationService) {
  }

  ngOnInit() {
    this.publicationUuid = this.route.snapshot.paramMap.get('uuid');
    this.publicationService.getPublication(this.publicationUuid).subscribe(
      (publication) => this.publication = publication,
      (error) => {
        this.router.navigateByUrl('');
        console.error(error)
      }
    )
  }

}
