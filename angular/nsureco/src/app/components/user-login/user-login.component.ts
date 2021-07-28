import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})
export class UserLoginComponent implements OnInit {
  constructor(private http: HttpClient) {
   }

  url: string = `http://localhost:8083/test`; //fake api created in policy service for test purposes
  userObj: Object;
  userName: string;
  userPassword: string;

  getUserData(userName: string, userPassword: string){
    this.userName = userName;
    this.userPassword = userPassword;
  }

  createRequestBody(){
    this.userObj = {
      userName: this.userName,
      userPassword: this.userPassword
    };
    this.postData();  //method is called after creating request body and sending it to user service
  }

  postData() {
    this.http.post<any>(this.url, this.userObj).subscribe(data => {
      this.userObj = data;
      console.log(data);
    })
  }

  ngOnInit(): void {
  }
}