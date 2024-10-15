import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CreditCardComponent } from './components/credit-card/credit-card.component';
import { CreditCardDetailsComponent } from './components/credit-card-details/credit-card-details.component';
import { CardListComponent } from './components/card-list/card-list.component';

@NgModule({
  declarations: [
    AppComponent,
    CreditCardComponent,
    CreditCardDetailsComponent,
    CardListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
