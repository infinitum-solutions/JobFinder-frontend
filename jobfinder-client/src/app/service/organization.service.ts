import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Organization} from "../model/organization";

@Injectable()
export class OrganizationService {

  private URL: string = '/api/organizations/';

  constructor(private http: HttpClient) {
  }

  createOrganization(organization: Organization): Observable<Organization> {
    return this.http.post<Organization>(this.URL, organization);
  }

  updateOrganization(organizationURL: string, organization: Organization): Observable<Organization> {
    return this.http.put<Organization>(this.URL + organizationURL, organization);
  }

  deleteOrganization(organizationURL: string, organization: Organization): Observable<Organization> {
    return this.http.delete<Organization>(this.URL + organizationURL);
  }

  getOrganizations(): Observable<Organization[]> {
    return this.http.get<Organization[]>(this.URL);
  }

  getOrganization(organizationUuid: string): Observable<Organization> {
    return this.http.get<Organization>(this.URL + organizationUuid);
  }
}
