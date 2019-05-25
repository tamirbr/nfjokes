import { Component } from '@angular/core';
import { ApiService } from './shared/api.service';
import { UserModel } from './register/model/register';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  greeting:any;
  loggedInUser:UserModel;
  authenticatedUser:boolean;

  constructor(private apiService: ApiService) {
    this.authenticatedUser = this.authenticated();
    //this.loggedInUser = JSON.parse(sessionStorage.getItem('userToken'));
    this.loggedInUser = apiService.loggedInUser;
  }
  title = 'nfjokes-ng';
  authenticated() { return this.apiService.authenticated; }
}
