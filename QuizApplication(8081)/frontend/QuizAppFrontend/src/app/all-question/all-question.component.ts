import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, catchError, tap, throwError } from 'rxjs';
import { Questions } from '../question';


const QUESTION_BASE_URL='http://localhost:8080/qestion/allQuestion';

@Component({
  selector: 'app-all-question',
  templateUrl: './all-question.component.html',
  styleUrl: './all-question.component.css'
})
export class AllQuestionComponent {

  constructor(private router:Router , private http : HttpClient ){}

  questionList:Questions[] = [] ;

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

  getAllQuestion(): Observable<Questions[]>{
    return this.http.get<Questions[]>(QUESTION_BASE_URL).pipe(
      tap(data => console.log('All' , JSON.stringify(data))),
      catchError(this.handleError)
    )
  }

  ngOnIt(){
    this.getAllQuestion().subscribe({
      next: response => {
        console.log(response)
        this.questionList=response
      },
      error: error=>console.error(error)
    })
  }

}
