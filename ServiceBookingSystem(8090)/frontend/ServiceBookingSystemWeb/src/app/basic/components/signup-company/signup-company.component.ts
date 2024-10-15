import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, throwError } from 'rxjs';

@Component({
  selector: 'app-signup-company',
  templateUrl: './signup-company.component.html',
  styleUrl: './signup-company.component.css'
})
export class SignupCompanyComponent {

  boolAdm=0;
  boolCus=1;

  constructor(private router : Router , private http : HttpClient){}



  private handleError(err : HttpErrorResponse){

    let errorMessage = "";
    if(err.error instanceof ErrorEvent){
      errorMessage= `An error has occured ${err.error.message}`;
    }else {
      errorMessage = `Server eturn code :${err.status} , Error Message is : ${err.message}`;
    }

    console.error(errorMessage);

    return throwError(() => errorMessage);
  }


  submitLogin(type : string , singupRequestDto:): Observable<>{

    return this.http.post<>().pipe(
      tap(data => console.log('All',JSON.stringify(data))),
      catchError(this.handleError)
    )    

  }

  onSubmitCompany(){
    console.log()
    this.submitLogin("/ForCompany",).subscribe(
      {
        next : res => console.log(res),
        error : err => console.log(err)
      })

      alert("Registration Successfull")
      // putting the proper routing
}


// onSubmitCompany(){
//   console.log()
//   this.submitLogin("/ForClient",).subscribe(
//     {
//       next : res => console.log(res),
//       error : err => console.log(err)
//     })

//     alert("Registration Successfull")
//     // putting the proper routing
// }

handleClient(){
  this.boolAdm=1;
  this.boolCus=0;
}

// handleCompany(){
//   this.boolAdm=0;
//   this.boolCus=1;
// }

}
