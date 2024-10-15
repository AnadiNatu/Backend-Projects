import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomePageComponent } from './home-page/home-page.component';
import { QuizCreatorComponent } from './quiz-creator/quiz-creator.component';
import { AllQuestionComponent } from './all-question/all-question.component';
import { QuestionCategoryComponent } from './question-category/question-category.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AddQuestionComponent } from './add-question/add-question.component';
import { CreateQuizComponent } from './create-quiz/create-quiz.component';
import { GetQuizComponent } from './get-quiz/get-quiz.component';
import { QuizContestantComponent } from './quiz-contestant/quiz-contestant.component';

@NgModule({
  declarations: [
    AppComponent,
    HomePageComponent,
    QuizContestantComponent,
    QuizCreatorComponent,
    AllQuestionComponent,
    QuestionCategoryComponent,
    AddQuestionComponent,
    CreateQuizComponent,
    AllQuestionComponent,
    GetQuizComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }



//app  module ts mai declare bhi karne hote hain component