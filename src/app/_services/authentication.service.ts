import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {map} from 'rxjs/operators';

@Injectable({providedIn: 'root'})
export class AuthenticationService {

  static key: string = 'currentUser';

  constructor(private http: HttpClient) {
  }

  // authenticate(credentials): Observable<boolean> {
  //   return this.http.post<boolean>('/login', credentials);
  // }

  login(username: string, password: string) {
    return this.http.post<any>(`/login`, {username, password})
      .pipe(map(user => {
        // login successful if there's a user in the response
        if (user) {
          // store user details and basic auth credentials in local storage
          // to keep user logged in between page refreshes
          user.authdata = window.btoa(username + ':' + password);
          localStorage.setItem(AuthenticationService.key, JSON.stringify(user));
        }

        return user;
      }));
  }

  static isAuthenticated(): boolean {
    return localStorage.getItem(AuthenticationService.key) != null;
  }

  static logout() {
    // remove user from local storage to log user out
    localStorage.removeItem(AuthenticationService.key);
  }
}
