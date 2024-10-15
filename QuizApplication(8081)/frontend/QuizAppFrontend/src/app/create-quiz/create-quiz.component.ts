import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { catchError, throwError } from 'rxjs';

@Component({
  selector: 'app-create-quiz',
  templateUrl: './create-quiz.component.html',
  styleUrl: './create-quiz.component.css'
})
export class CreateQuizComponent {

  createQuizForm : FormGroup;

  private readonly BASIC_QUIZ_URL = 'http://localhost:8080/quiz/create'

  constructor(private fb : FormBuilder, private http : HttpClient){

  }

  ngOnInIt(){

    this.createQuizForm = this.fb.group({
      category:['' , Validators.required],
      numQ:['' , Validators.required , Validators.min(1)],
      title:['' , Validators.required],
    })
  }

  onSubmit() : void{

    if(this.createQuizForm.valid){

      const {category,numQ,title} = this.createQuizForm.value;

      this.http.post(this.BASIC_QUIZ_URL,null,{params:{category, numQ , title}
      }).pipe(
        catchError(this.handleError)
      ).subscribe(response => {
        console.log('Quiz Created Successfully' , response);
        this.createQuizForm.reset();
      });
    }else{
      console.error('Form is Invalid');
    }
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
