import {Observable} from "rxjs";
import {Person} from "../model/person";
import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Publication} from "../model/publication";

@Injectable()
export class PersonService {

  private URL: string = '/api/persons/';

  constructor(private http: HttpClient) {
  }

  updatePerson(personURL: string, person: Person): Observable<Person> {
    return this.http.put<Person>(this.URL + personURL, person)
  }

  getPerson(link: string): Observable<Person> {
    return this.http.get<Person>(this.URL + link);
  }

  getPersons(): Observable<Person[]> {
    return this.http.get<Person[]>(this.URL);
  }

  getPersonPublications(personUuid: string) : Observable<Publication[]> {
    return this.http.get<Publication[]>(this.URL + personUuid + '/publications')
  }
}
