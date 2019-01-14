import {Component, OnInit, ViewChild} from '@angular/core';
import {NgForm} from "@angular/forms";
import {Router} from "@angular/router";
import {PublicationService} from "../../service/publication.service";

@Component({
  selector: 'app-publication-create',
  templateUrl: './publication-create.component.html',
  providers: [PublicationService],
  styleUrls: ['./publication-create.component.css']
})
export class PublicationCreateComponent implements OnInit {

  @ViewChild("publication") private publicationForm: NgForm;

  constructor(private publicationService: PublicationService,
              private router: Router
  ) {
  }

  ngOnInit() {
  }

  onSubmit() {
    this.publicationService.createPublication(this.publicationForm.value).subscribe(
      (publication) => {
        this.router.navigateByUrl('/publications/' + publication.uuid);
      },
      (error) => console.error(error)
    );
  }

}
