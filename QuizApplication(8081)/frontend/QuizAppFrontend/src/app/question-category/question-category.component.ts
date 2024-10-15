import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Questions } from '../question';
import { Observable, catchError, tap, throwError } from 'rxjs';
import { FormBuilder, FormGroup } from '@angular/forms';
import { OnInit } from '@angular/core';


@Component({
  selector: 'app-question-category',
  templateUrl: './question-category.component.html',
  styleUrl: './question-category.component.css'
})
export class QuestionCategoryComponent implements OnInit {

  quizForm: FormGroup;
  questionList: Questions[]=[];

  constructor(private http : HttpClient, private fb : FormBuilder){
    this.quizForm = this.fb.group({
      category:['']
    });
  }
  ngOnInit(): void {

      this.loadQuestions();
  }

  getQuestionByCategory(category : string): Observable<Questions[]>{

    const url = category ? 'http://localhost:8080/qestion/category/{category}' : 'http://localhost:8080/qestion/allQuestion';

    return this.http.get<Questions[]>(url).pipe(
      tap(data => console.log('All',JSON.stringify(data))),
      catchError(this.handleError)
    );

  }

  loadQuestions(category?: string):void{
    this.getQuestionByCategory(category || '').subscribe(data =>{
      this.questionList=data;
    })
  }

  onSubmit():void{

    const category = this.quizForm.get('category')?.value;
    this.loadQuestions(category)

  }

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



  


}
