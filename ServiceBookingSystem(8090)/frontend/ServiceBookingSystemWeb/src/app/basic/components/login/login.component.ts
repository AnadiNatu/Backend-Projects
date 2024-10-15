import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth/auth.service';
import { throwError } from 'rxjs';
import { UserStorageService } from '../../services/storage/user-storage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  constructor(private route:Router,private http:HttpClient ,  private authService : AuthService){

  }




  private handleError(err:HttpErrorResponse){
    let errorMessage='';

    if(err.error instanceof ErrorEvent){
      errorMessage = `An error occured: ${err.error.message} `
    }else{
      errorMessage = `Server Return Code : ${err.status} , Error Message is : ${err.message}`
    }

    console.error(errorMessage);
    return throwError(()=> errorMessage)
  }

  onSubmitClient(){
//auth service mai se login method + credit card wale log in ko mix karna hain 
    this.authService.login()

    if(UserStorageService.isClientLoggedIn){
      this.route.navigateByUrl('client/dashboard')
    }else if(UserStorageService.isCompanyLoggedIn){
      this.route.navigateByUrl('companay/dashboard')
    }
  }

}
