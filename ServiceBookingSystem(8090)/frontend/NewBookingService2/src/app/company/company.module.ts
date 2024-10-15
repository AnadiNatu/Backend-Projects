import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CompanyRoutingModule } from './company-routing.module';
import { CompanyComponent } from './company.component';
import { AllAdsComponent } from './pages/all-ads/all-ads.component';
import { CompanyDashboardComponent } from './pages/company-dashboard/company-dashboard.component';
import { CreateAdComponent } from './pages/create-ad/create-ad.component';
import { UpdateAdComponent } from './pages/update-ad/update-ad.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from '../app-routing.module';


@NgModule({
  declarations: [
    CompanyComponent,
    AllAdsComponent,
    CompanyDashboardComponent,
    CreateAdComponent,
    UpdateAdComponent
  ],
  imports: [
    CommonModule,
    CompanyRoutingModule,
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
  ],
  bootstrap : [CompanyComponent]
})
export class CompanyModule { }
