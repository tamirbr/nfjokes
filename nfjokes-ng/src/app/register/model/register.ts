export interface RegisterUserModel{
    name:string;
    email:string;
    password:string;
    multipartImage:File;
    rePassword:string;
    terms:boolean;
  }
  
  export interface UserModel{
    name:string;
    email:string;
    password:string;
    image:File;
    role:string;
    enabled:boolean;
    nonLocked:boolean;
  }

  export interface LoginUserModel{
    email:string;
    password:string;
    rememberMe:boolean;
  }