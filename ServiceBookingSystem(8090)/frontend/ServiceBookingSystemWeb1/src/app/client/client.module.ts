import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ClientRoutingModule } from './client-routing.module';
import { ClientComponent } from './client.component';
import { ClientDashboardComponent } from './pages/client-dashboard/client-dashboard.component';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from '../app-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { DemoNgZorroAntdModule } from '../DemoNgZorroAntdModule';
import { AdDetailsComponent } from './pages/ad-details/ad-details.component';
import { MyBookingsComponent } from './pages/my-bookings/my-bookings.component';
import { ReviewComponent } from './pages/review/review.component';
import { NZ_I18N, en_US } from 'ng-zorro-antd/i18n';


@NgModule({
  declarations: [
    ClientComponent,
    ClientDashboardComponent,
    AdDetailsComponent,
    MyBookingsComponent,
    ReviewComponent
  ],
  imports: [
    CommonModule,
    ClientRoutingModule,
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    BrowserAnimationsModule,
    DemoNgZorroAntdModule,
    ReactiveFormsModule

  ],
  providers: [{provide: NZ_I18N , useValue: en_US}]
})
export class ClientModule { }
