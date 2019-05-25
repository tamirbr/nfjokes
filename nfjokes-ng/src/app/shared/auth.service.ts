import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserModel } from '../register/model/register';
import { Router } from '@angular/router';
import { ApiService } from './api.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  authenticated = false;
  loggedInUser:UserModel;

  constructor(private http: HttpClient, private router: Router, private apiService: ApiService) {
    if(this.authenticated == false && sessionStorage.getItem('userToken')){
      this.loggedInUser = JSON.parse(sessionStorage.getItem('userToken'));
      this.authenticated = true;
    }
   }

   authenticate(credentials:any) {
    if(this.authenticated == false){
      this.http.post<UserModel>(this.apiService.BASE_URL+"/login", {
        email: credentials.email,
        password: credentials.password
      }).subscribe(user => {
          if (user != null) {
              sessionStorage.setItem('userToken', JSON.stringify(user));
              this.authenticated = true;
              this.loggedInUser = user;
              this.router.navigate(['/']);
          } else {
              alert("Login failed.")
          }
      });
    }
}

logout() {
  sessionStorage.removeItem('userToken');
  this.authenticated = false;
  this.loggedInUser = null;
  this.router.navigate(['']);
}

}
