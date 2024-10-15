import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ICustomer } from '../../customer';
import { Observable, catchError, tap, throwError } from 'rxjs';


const REGISTER_BASE_URL = "http://localhost:8802/Registration"

@Component({
  selector: 'app-register-page',
  standalone: true,
  imports: [],
  templateUrl: './register-page.component.html',
  styleUrl: './register-page.component.css'
})
export class RegisterPageComponent {


  constructor(private router : Router , private http : HttpClient){

  }

  boolAdm=0;
  boolCus=1;
  

  customerDetC:ICustomer={
    "id":0,
    "fname":"",
    "lname":"",
    "age":"",
    "phoneNumber":"",
    "dateOfBirth":"",
    "email":"",
    "password":"",
    "address":"",
    "addressProof":""
  }

  customerDetA:ICustomer={
    "id":0,
    "fname":"",
    "lname":"",
    "age":"",
    "phoneNumber":"",
    "dateOfBirth":"",
    "email":"",
    "password":"",
    "address":"",
    "addressProof":""
  }

  private handleError(err:HttpErrorResponse){
    let errorMessage ="";
    if(err.error instanceof ErrorEvent){
      errorMessage= `An error has occured ${err.error.message}`;
    }else{
      errorMessage = `Server return code :${err.status} , Error Message is: ${err.message}`
    }

    console.error(errorMessage);

    return throwError(() => errorMessage)
  }

  submitLogin(type : string , customerDetails:ICustomer) :Observable<ICustomer>{
    return this.http.post<ICustomer>(REGISTER_BASE_URL+type,customerDetails).pipe(
      tap(data => console.log('All',JSON.stringify(data))),
      catchError(this.handleError)
    )
  }


  onSubmitC(){
    console.log(this.customerDetC)
    this.submitLogin("/ForCustomer",this.customerDetC).subscribe(
      {
        next : res => console.log(res),
        error : err => console.log(err)
      })

      alert("Registration Successfull")
      this.router.navigate(['/'])
  }

    onSubmitA(){
      console.log(this.customerDetC)
      this.submitLogin("/ForAdmin",this.customerDetA).subscribe(
        {
          next : res => console.log(res),
          error : err => console.log(err)
        })
  
        alert("Registration Successfull")
        this.router.navigate(['/'])
  }

      handleAdm(){
        this.boolAdm=1;
        this.boolCus=0;
    }

      handleCus(){
        this.boolAdm=0;
        this.boolCus=1;
    }


}

