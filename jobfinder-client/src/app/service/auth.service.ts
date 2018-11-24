import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {environment} from "../../environments/environment";

@Injectable()
export class AuthService {

  private loginURL: string = environment.apiURL + '/login';
  private logoutURL: string = environment.apiURL + '/logout';

  authenticated: boolean = false;

  constructor(
    private http: HttpClient,
  ) {
  }

  authenticate(credentials, callback) {

    const headers = new HttpHeaders(credentials ? {
      authorization : 'Basic ' + btoa(credentials.username + ':' + credentials.password)
    } : {});

    this.http.post(this.loginURL, {headers: headers}).subscribe(response => {
      this.authenticated = !!response['name'];
      return callback && callback();
    });

  }

  logout(callback) {
    this.http.post(this.logoutURL, {}).subscribe(
      () => {
        this.authenticated = false;
        return callback && callback();
      }
    );
  }

}
