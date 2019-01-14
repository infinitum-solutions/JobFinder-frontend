import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../model/user";
import {Injectable} from "@angular/core";

@Injectable()
export class UserService {

  private URL: string = '/api/user/';

  constructor(private http: HttpClient) {
  }

  getUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.URL);
  }
}
