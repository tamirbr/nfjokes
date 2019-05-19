import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
 
  model: UserModel = {
    email:'',
    name:'',
    password:'',
    image:null,
    multipartImage:null,
    rePassword:'',
    terms:false
  };


  constructor(private http: HttpClient) { 

  }

  ngOnInit() {
  }

  register(): void{
    let url = "http://localhost:8080/api/users/register";
    this.http.post(url, this.model).subscribe(
      res => {
        location.reload();
      },
      err =>{
        alert("Error");
      }
    );
  }

  toggleVisibility(e){
    this.model.terms= e.target.checked;
  }

  public onFileSelected(event) {
    //this.multipartImage = <File>event.target.files[0];
  }

}

export interface UserModel{
  name:string;
  email:string;
  password:string;
  image:File;
  multipartImage:File;
  rePassword:string;
  terms:boolean;
}