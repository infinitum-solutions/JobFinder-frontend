import {Person} from "./person";

export interface Organization {
  id: number;
  link: string;
  title: string;
  owner: Person;
  members: Person[];
  subscribers: Person[];
}
