import { Component } from '@angular/core';
import {AuthenticationService} from "./_services";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  constructor(private authService: AuthenticationService) {
  }

  isAuthenticated(): boolean {
    return AuthenticationService.isAuthenticated();
  }

  logout() {
    AuthenticationService.logout();
  }
}
