import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Observable} from "rxjs";

@Injectable()
export class AuthService {

  authenticated: boolean = false;

  constructor(
    private http: HttpClient,
  ) {
  }

  authenticate(credentials): Observable<boolean> {
    return this.http.post<boolean>('/login', credentials);
  }

  logout(callback) {
    this.http.post('/logout', {}).subscribe(
      () => {
        this.authenticated = false;
        return callback && callback();
      }
    );
  }

}
