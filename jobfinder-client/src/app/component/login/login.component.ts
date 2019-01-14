import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../service/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  providers: [AuthService],
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  credentials = {username: '', password: ''};

  constructor(
    private authService: AuthService,
    private router: Router
  ) {
  }

  ngOnInit() {
    sessionStorage.setItem('token', '');
  }

  login() {
    this.authService.authenticate(this.credentials).subscribe(isValid => {
      if (isValid) {
        sessionStorage.setItem(
          'token',
          btoa(this.credentials.username + ':' + this.credentials.password)
        );
        this.router.navigateByUrl('');
      } else {
        alert("Authentication failed.")
      }
    });
  }

}
