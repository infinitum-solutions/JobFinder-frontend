import {Component, Input, OnInit} from '@angular/core';
import {Publication} from "../../_models/publication";
import {PublicationService} from "../../_services/publication.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-publication',
  templateUrl: './publication.component.html',
  providers: [PublicationService],
  styleUrls: ['./publication.component.css']
})
export class PublicationComponent implements OnInit {

  @Input() publication: Publication;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private publicationService: PublicationService) {
  }

  ngOnInit() {
    if (this.publication == null) {
      let publicationUuid = this.route.snapshot.paramMap.get('publicationUuid');
      this.publicationService.getPublication(publicationUuid).subscribe(
        (publication) => this.publication = publication,
        (error) => {
          this.router.navigateByUrl('');
          console.error(error)
        }
      )
    }
  }

}
