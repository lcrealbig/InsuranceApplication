import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})
export class UserLoginComponent implements OnInit {
  constructor(private http: HttpClient, private router: Router) {
   }

  url: string = `http://localhost:8082/verify`; 
  userObj: Object;
  userName: string;
  userPassword: string;
  wrongData: string = '';

  getUserData(userName: string, userPassword: string){  //getting users inputs from html form
    this.userName = userName;
    this.userPassword = userPassword;
    this.createRequestBody(); //method is called to create object with user data
  }

  createRequestBody(){
    this.userObj = {
      userName: this.userName,
      userPassword: this.userPassword
    };
    this.postUser();  //method is called after creating request body and sending it to user service
  }

  postUser() {
    this.http.post<any>(this.url, this.userObj).subscribe(data => {
      this.userObj = data;
      if (this.userObj['name'] === 'NOT_EXIST') {
        this.wrongData = 'User name or password is incorrect';
      } else {
        this.wrongData = '';
        this.router.navigateByUrl('/userpanel');
      }
    })
  }

  ngOnInit(): void {
  }
}