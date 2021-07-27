import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})
export class UserLoginComponent implements OnInit {
  constructor(private http: HttpClient) { }

  url: string = `http://localhost:8083/test`; //fake api created in policy service for test purposes
  userObj: Object;
  userObjString: string;
  userName: string;
  userPassword: string;

  getUserName(userName: string) {
    this.userName = userName;
  }

  getUserPassword(userPassword: string) {
    this.userPassword = userPassword;
  }

  createRequestBody(){
    this.userObj = {
      userName: this.userName,
      userPassword: this.userPassword
    };
    this.userObjString = JSON.stringify(this.userObj);
    console.log(this.userObjString);
  }

  // postData() {
  //   this.http.post(this.url, this.userObjString).subscribe(data => {
  //     data
  //   });
  // }

  ngOnInit(): void {

  }
}