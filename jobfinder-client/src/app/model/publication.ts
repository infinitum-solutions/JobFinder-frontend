import {Person} from "./person";

export class Publication {
  author: Person;
  link: string;
  title: string;
  description: string;
  content: string;
  visible: boolean;

  constructor(title: string, description: string, content: string, visible: boolean) {
    this.title = title;
    this.description = description;
    this.content = content;
    this.visible = visible;
  }
}
