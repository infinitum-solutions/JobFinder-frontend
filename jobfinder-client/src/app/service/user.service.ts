import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../model/user";
import {Injectable} from "@angular/core";
import {environment} from "../../environments/environment";

@Injectable()
export class UserService {

  private URL: string = environment.apiURL + '/api/user/';

  constructor(private http: HttpClient) {
  }

  getUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.URL);
  }
}
