import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { questionWrapper } from '../questionWrapper';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { response } from '../response';

@Component({
  selector: 'app-get-quiz',
  templateUrl: './get-quiz.component.html',
  styleUrl: './get-quiz.component.css'
})
export class GetQuizComponent {

  quizForm:FormGroup;
  quizIdForm:FormGroup;
  currentQuestionIndex:number = 0;
  quizQuestion:questionWrapper[] = [];
  responses: response[];
  result: number | null = null;
  private readonly QUIZ_BASE_URL='http://localhost:8080/quiz'


  constructor(private fb:FormBuilder , private http: HttpClient){

  }

  ngONInIt(){
    this.quizForm = this.fb.group({
      response: ['', Validators.required]
    });

    this.quizIdForm = this.fb.group({
      quizId: ['' , Validators.required]
    });
  }


  onSubmitQuizId(): void {

    if(this.quizIdForm.valid){
      const quizId= this.quizIdForm.get('quizId')?.value;
      this.getQuizById(quizId).subscribe(data => {
        this.quizQuestion = data.questions;
        this.currentQuestionIndex=0;
        this.responses=[];
        this.result=null;
        this.quizForm.reset();
      });
    } else {
      console.error('Quiz ID form is Invalid')
    }
  }

  onNext(): void{

    const currentResponse = this.quizForm.value.response;

    this.responses.push({
      id: this.quizQuestion[this.currentQuestionIndex].id,
      response:currentResponse
    });
    this.currentQuestionIndex++;
    this.quizForm.reset();

  }

  onSubmit(): void {

    const currentResponse = this.quizForm.value.response;

    this.responses.push({
      id : this.quizQuestion[this.currentQuestionIndex].id,
      response : currentResponse
    });

    this.submitQuizResponses().subscribe(result => {
      this.result=result;
    })
  }



  private getQuizById(id:number): Observable<{questions: questionWrapper[]}>{

    return this.http.get<{questions:questionWrapper[]}>(`${this.QUIZ_BASE_URL}/get/${id}`).pipe(
      catchError(this.handleError)
    );
  }

  private submitQuizResponses(): Observable<number>{
    const quizId = this.quizIdForm.value.quizId;

    return this.http.post<number>(`${this.QUIZ_BASE_URL}/submit/${quizId}`,this.responses).pipe(
      catchError(this.handleError)
    )
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



