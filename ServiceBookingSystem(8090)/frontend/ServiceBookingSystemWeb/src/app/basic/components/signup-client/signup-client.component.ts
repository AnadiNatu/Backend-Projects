import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient , HttpErrorResponse } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';

@Component({
  selector: 'app-signup-client',
  templateUrl: './signup-client.component.html',
  styleUrl: './signup-client.component.css'
})
export class SignupClientComponent {

  boolAdm=0;
  boolCus=1;

  constructor(private router : Router , private http : HttpClient){}
// authService implement karni hain yahan aur company signup main


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

  onSubmitClient(){
    console.log()
    this.submitLogin("/ForClient",).subscribe(
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
