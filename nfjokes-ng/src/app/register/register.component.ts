import { Component, OnInit, ViewChild } from '@angular/core';
import { RegisterUserModel, LoginUserModel } from './model/register';
import { ApiService } from '../shared/api.service';
import { Router } from '@angular/router';
import { AuthService } from '../shared/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
 
  model: RegisterUserModel = {
    email:'',
    name:'',
    password:'',
    multipartImage:null,
    rePassword:'',
    terms:false
  };

  loginModel: LoginUserModel = {
    email:'',
    password:'',
    rememberMe:false
  };

  @ViewChild('register') formValues: any;
  registerMessage:string;
  fail:boolean;
  emailCheck:boolean;

  loginMessage:string;

  constructor(private apiService: ApiService,private authService: AuthService, private router: Router) { 

  }

  ngOnInit() {

  }

  registration(): void{
    this.apiService.registrUser(this.model).subscribe(
      res => {
        this.registerMessage = 'Registered successfully';
        this.fail = false;
        this.formValues.resetForm();
      },
      err =>{
        this.registerMessage = 'Registration failed';
        this.fail = true;
      }
    );
  }

  login() {
    this.authService.authenticate(this.loginModel);
    return false;
  }

  toggleVisibility(modelType:any,modelField:any,e:any){
    this[modelType][modelField] = e.target.checked;
  }

  emailAvailability(register:any){
    if(this.model.email){
      this.apiService.getUserByEmail(this.model.email).subscribe(
        res=>{
          var emailRes = (res) ? true : false;
          if(emailRes){
            register.form.controls['email'].setErrors({'use': emailRes});
          }
        },
        err =>{
          
        }
      );
    }
  }

  selectFile(event:any) {
    let file:File = event.target.files[0];
    if (file.type.match('image.*')) {
      this.model.multipartImage = file;
    } else {
      alert('invalid format!');
    }
  }

}

