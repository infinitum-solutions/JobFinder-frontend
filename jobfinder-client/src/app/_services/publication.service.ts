import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Publication} from "../_models/publication";
import {Injectable} from "@angular/core";

@Injectable()
export class PublicationService {

  private URL: string = '/api/publications/';

  constructor(private http: HttpClient) {
  }

  createPublication(publication: Publication): Observable<Publication> {
    return this.http.post<Publication>(this.URL, publication);
  }

  updatePublication(publicationURL:string, publication: Publication): Observable<Publication> {
    return this.http.put<Publication>(this.URL + publicationURL, publication);
  }
  
  deletePublication(publicationURL:string): Observable<Publication> {
    return this.http.delete<Publication>(this.URL + publicationURL);
  }

  getPublication(link: string): Observable<Publication> {
    return this.http.get<Publication>(this.URL + link);
  }

  getPublications(): Observable<Publication[]> {
    return this.http.get<Publication[]>(this.URL);
  }
}
