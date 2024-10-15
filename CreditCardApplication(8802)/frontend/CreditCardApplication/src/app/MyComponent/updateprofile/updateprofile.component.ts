import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, catchError, tap, throwError } from 'rxjs';
import { ICustomer } from '../../customer';

const UPDATE_CUST_URL="http://localhost:8802/Customers/UpdateCustomer"

@Component({
  selector: 'app-updateprofile',
  standalone: true,
  imports: [],
  templateUrl: './updateprofile.component.html',
  styleUrl: './updateprofile.component.css'
})
export class UpdateprofileComponent {

  constructor(private router:Router,private http:HttpClient){}


  id:string='';

  customerDet : ICustomer={
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


  private handleError(err : HttpErrorResponse){

    let errorMessage=''
    if(err.error instanceof ErrorEvent){
      errorMessage = `An error occured: ${err.error.message}`
    }else{
      errorMessage = `Server Return Code: ${err.status} , Error Message is: ${err.message}`
    }
    console.error(errorMessage);
    return throwError(()=> errorMessage)
  }


  submitLogin(id:string , customerDetails : ICustomer): Observable<ICustomer>{
    return this.http.put<ICustomer>(UPDATE_CUST_URL+id,customerDetails).pipe(
      tap(data => console.log('All',JSON.stringify(data))),
      catchError(this.handleError)
    )
  }

  onUpdateC(){
    this.id = window.location.href.substring(36,undefined)
    console.log(this.customerDet)
    this.submitLogin(this.id,this.customerDet).subscribe({
      next : res => {
        console.log(res)
        this.router.navigate(['/adminprofile'])
      },
      error : err => console.log(err)
    })
  }

  
}
