import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomePageComponent } from './home-page/home-page.component';
import { QuizCreatorComponent } from './quiz-creator/quiz-creator.component';
import { AllQuestionComponent } from './all-question/all-question.component';
import { QuestionCategoryComponent } from './question-category/question-category.component';
import { QuizContestantComponent } from './quiz-contestant/quiz-contestant.component';
import { CreateQuizComponent } from './create-quiz/create-quiz.component';
import { GetQuizComponent } from './get-quiz/get-quiz.component';
import { AddQuestionComponent } from './add-question/add-question.component';

const routes: Routes = [{path: '', component:HomePageComponent},
                        {path: 'creator' , component:QuizCreatorComponent},
                        {path: 'contestent' , component:QuizContestantComponent},
                        {path: 'addQuestion' , component:AddQuestionComponent},
                        {path: 'allQuestion' , component:AllQuestionComponent},
                        {path: 'categoryQuestion' , component:QuestionCategoryComponent},
                        {path: 'createQuiz' , component:CreateQuizComponent},
                        {path: 'getQuiz' , component:GetQuizComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
