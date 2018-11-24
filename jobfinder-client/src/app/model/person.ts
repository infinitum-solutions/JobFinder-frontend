import {Organization} from "./organization";
import {Publication} from "./publication";

export interface Person {
  id: number;
  link: string;
  firstName: string;
  secondName: string;
  lastName: string;
  age: number;
  sex: string;
  country: string;
  publications: Publication[];
  subscribedOrganizations: Organization[];
  memberOrganizations: Organization[];
}
