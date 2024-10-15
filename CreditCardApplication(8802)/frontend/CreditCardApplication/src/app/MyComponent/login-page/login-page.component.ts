import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ILogin } from '../../login';
import { Observable, catchError, tap, throwError } from 'rxjs';


const LOGIN_BASE_URL='http://localhost:8802/Login/Authenticate'

@Component({
  selector: 'app-login-page',
  standalone: true,
  imports: [],
  templateUrl: './login-page.component.html',
  styleUrl: './login-page.component.css'
})
export class LoginPageComponent {

  constructor(private route:Router,private http:HttpClient){

  }

  boolAdm=0;
  boolCus=1;
  id=0;

  loginDetailC:ILogin={
    "email":"",
    "password":""
    }

  loginDetailA:ILogin={
      "email":"",
      "password":""
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

  
  submitLogin(type:string , loginDetail:ILogin): Observable<number>{

    return this.http.post<number>(LOGIN_BASE_URL+type,loginDetail).pipe(
      tap(data => console.log('All',data)),
      catchError(this.handleError)
    )
  }

  onSubmitC(){
    this.submitLogin("/Customer",this.loginDetailC).subscribe({
      next: response =>{
        this.id=response
        this.route.navigate(["/customerprofile/"+this.id])
      },
      error: error => console.error(error)
    })
  }

  onSubmitA(){
    this.submitLogin("/Admin",this.loginDetailA).subscribe({
      next: response =>{
        this.id=response
        this.route.navigate(["/adminrofile/"+this.id])
      },
      error: error => console.error(error)
    })
    this.route.navigate(["/adminprofile"])
  }

  handleCus(){
    this.boolAdm=0;
    this.boolCus=1;
  }

  handleAdm(){
    this.boolAdm=1;
    this.boolCus=0;
  }

}
