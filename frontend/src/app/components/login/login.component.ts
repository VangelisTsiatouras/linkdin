import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {HttpClient} from '@angular/common/http';
import {Response, Headers, RequestOptions, Http} from '@angular/http';
import {User} from '../../models/user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {
  user: User = new User();

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private http: HttpClient,
    // private http_: Http
  ) { }

  ngOnInit() {
  }

  // THE BELOW IS A TEST!! I AM PASSING FIXED JSON TO SERVER
  loginUser() {
    console.log(this.user);
    const email = this.user.email;
    const pass = this.user.password;
    const req = this.http.post('http://localhost:8080/api/login', {
      email: email,
      password: pass
    })
      .subscribe(
        res => {
          console.log(res);
        },
        err => {
          console.log('Error occured');
        }
      );
  }

}
