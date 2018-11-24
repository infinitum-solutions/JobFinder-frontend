import {Component, Input, OnInit} from '@angular/core';
import {Publication} from "../../../../model/publication";

@Component({
  selector: 'app-publication',
  templateUrl: './publication-item.component.html',
  styleUrls: ['./publication-item.component.css']
})
export class PublicationItemComponent implements OnInit {

  @Input() publication: Publication;

  constructor() { }

  ngOnInit() {
  }

}
