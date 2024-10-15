import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CardListComponent } from './components/card-list/card-list.component';

const routes: Routes = [{path : 'cards' ,component : CardListComponent},
                        {path : '' , redirectTo : '/cards' , pathMatch:'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
