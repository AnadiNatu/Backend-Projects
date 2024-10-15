import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ICustomerRec } from '../../customerRec';
import { Observable, catchError, take, tap, throwError } from 'rxjs';


const CUST_BASE_URL='http://localhost:8802/Customers/Customer/'

@Component({
  selector: 'app-customerprofile',
  standalone: true,
  imports: [],
  templateUrl: './customerprofile.component.html',
  styleUrl: './customerprofile.component.css'
})
export class CustomerprofileComponent implements OnInit {

  constructor(private router:Router , private http:HttpClient){}
 
  id:string = "";

  customer:ICustomerRec={
    "id":0,
    "name":"",
    "phoneOfNumber":"",
    "age":"",
    "dateOfBirth":"",
    "email":"",
    "password":"",
    "address":"",
    "addressProof":""
  }

  private handleError(err:HttpErrorResponse){

    let errorMessage='';

    if(err.error instanceof ErrorEvent){
      errorMessage=`An error occured: ${err.error.message}`
    }else{
      errorMessage=`Server return code : ${err.status} , Error message is ${err.message}`
    }

    console.error(errorMessage);
    return throwError(()=> errorMessage)

  }


  getCustomer(id:string):Observable<ICustomerRec> {
   return this.http.get<ICustomerRec>(CUST_BASE_URL+id).pipe(
    tap(data => console.log('All',JSON.stringify(data))),
    catchError(this.handleError)
   )
  }

  ngOnInit(): void {
    this.id=window.location.href.substring(38,undefined)
    this.getCustomer(this.id).subscribe({
      next: response => {
        console.log(response)
        this.customer=response
      },
      error: error=>console.error(error)
    })
  }


  

}
