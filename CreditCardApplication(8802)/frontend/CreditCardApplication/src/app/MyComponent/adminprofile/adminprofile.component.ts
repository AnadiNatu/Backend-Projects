import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ICustomerRec } from '../../customerRec';
import { Observable, catchError, tap, throwError } from 'rxjs';


const CUST_BASE_URL='http://localhost:8802/Customers/GetAllCustomer'
const CUST_DELETE_URL='http://localhost:8802/Customer/DeleteCustomer'
@Component({
  selector: 'app-adminprofile',
  standalone: true,
  imports: [],
  templateUrl: './adminprofile.component.html',
  styleUrl: './adminprofile.component.css'
})
export class AdminprofileComponent implements OnInit {
  
  constructor(private router:Router , private http:HttpClient){}

  customerList:ICustomerRec[] = []

  private handleError(err : HttpErrorResponse){
    let errorMessage='';
    if(err.error instanceof ErrorEvent){
      errorMessage=`Error status : ${err.error.message}`
    }else{
      errorMessage=`Error status : ${err.status} , error message is : ${err.message}`
    }

    console.error(errorMessage);
    return throwError(()=> errorMessage)
  }


  getAllCustomer() : Observable<ICustomerRec[]> {
     return this.http.get<ICustomerRec[]>(CUST_BASE_URL).pipe(
      tap(data => console.log('All',JSON.stringify(data))),
      catchError(this.handleError)
      
     )
  }
  
  
  ngOnInit(): void {
    this.getAllCustomer().subscribe({
      next: response  => {
         console.log(response)
         this.customerList=response
      },
      error: error=>console.error(error)
    })
  }

  deleteCustomer(id:string):Observable<any>{
    return this.http.delete<any>(CUST_DELETE_URL+id).pipe(
      tap(data => console.log('All',JSON.stringify(data))),
      catchError(this.handleError)
    )
  }


  handleDelete(id:number){

    this.deleteCustomer(id.toString()).subscribe({
      next: response => {
        console.log(response)
        this.customerList=response
      },
      error: error => console.error(error)
    })

    window.location.reload()
  }




}
