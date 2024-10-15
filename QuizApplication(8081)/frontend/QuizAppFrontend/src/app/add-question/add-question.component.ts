import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { catchError, throwError } from 'rxjs';




@Component({
  selector: 'app-add-question',
  templateUrl: './add-question.component.html',
  styleUrl: './add-question.component.css'
})
export class AddQuestionComponent {

 private readonly QUESTION_BASE_URL='http://localhost:8080/qestion/add';


  addQuestionForm: FormGroup;

  constructor(private fb : FormBuilder , private http : HttpClient ){

  } 


  ngOnInIt(){

    this.addQuestionForm = this.fb.group({
      questionTitle: ['' , Validators.required],
      option1 : ['' , Validators.required],
      option2: ['' , Validators.required],
      option3: ['' , Validators.required],
      option4: ['' , Validators.required],
      rightAnswer: ['' , Validators.required],
      difiicultylevel: ['' , Validators.required],
      category: ['' , Validators.required],
    })
  }

  //form se values le kar interface populate kar rahen hain , creating object
  onSubmit(){

    if(this.addQuestionForm.valid){
      const newQuestion = this.addQuestionForm.value;
    

    this.http.post(this.QUESTION_BASE_URL, newQuestion).pipe(
      catchError(this.handleError)
    ).subscribe(response => {
      console.log('Question added successfully', response);
      this.addQuestionForm.reset();
    })
  } else {

    console.error('Form is invalid');
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
