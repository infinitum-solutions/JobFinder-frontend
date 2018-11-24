import { Component } from '@angular/core';
import {Router} from "@angular/router";
import {AuthService} from "./service/auth.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  providers: [AuthService],
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'spring boot and angular 4 secure authentication';
  static API_URL="http://localhost:8090";

  constructor(
    private router: Router,
    private authService: AuthService
  ) {
    this.authService.authenticate(undefined, undefined);
  }

  logout() {
    this.authService.logout(
      () => {
        this.router.navigateByUrl('/login');
      }
    );
  }
}
