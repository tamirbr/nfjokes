import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserModel, LoginUserModel } from '../register/model/register';
import { Router } from '@angular/router';
import { AppComponent } from '../app.component';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  BASE_URL = "http://localhost:8080";
  USERS = "/api/users";

  authenticated = false;
  loggedInUser:UserModel;

  constructor(private http: HttpClient, private router: Router) { 

  }

  getUserByEmail(email:string): Observable<UserModel[]> {
    return this.http.get<UserModel[]>(this.BASE_URL+this.USERS+"/email/"+email);
  }

  registrUser(model:any): Observable<UserModel> {
    return this.http.post<UserModel>(this.BASE_URL+this.USERS+"/register", model);
  }
}
