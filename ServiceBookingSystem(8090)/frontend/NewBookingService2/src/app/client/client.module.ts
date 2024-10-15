import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ClientRoutingModule } from './client-routing.module';
import { ClientComponent } from './client.component';
import { AdDetailsComponent } from './pages/ad-details/ad-details.component';
import { ClientDashboardComponent } from './pages/client-dashboard/client-dashboard.component';
import { MyBookingsComponent } from './pages/my-bookings/my-bookings.component';
import { ReviewComponent } from './pages/review/review.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from '../app-routing.module';


@NgModule({
  declarations: [
    ClientComponent,
    AdDetailsComponent,
    ClientDashboardComponent,
    MyBookingsComponent,
    ReviewComponent
  ],
  imports: [
    CommonModule,
    ClientRoutingModule,
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
  ],
  bootstrap: [ClientComponent]
})
export class ClientModule { }
