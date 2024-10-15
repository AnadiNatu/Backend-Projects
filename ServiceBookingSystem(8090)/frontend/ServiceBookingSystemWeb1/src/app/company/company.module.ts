import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CompanyRoutingModule } from './company-routing.module';
import { CompanyComponent } from './company.component';
import { CompanyDashboardComponent } from './pages/company-dashboard/company-dashboard.component';
import { CreateAdComponent } from './pages/create-ad/create-ad.component';
import { DemoNgZorroAntdModule } from '../DemoNgZorroAntdModule';
import { ReactiveFormsModule } from '@angular/forms';
import { AllAdsComponent } from './pages/all-ads/all-ads.component';
import { UpdateAdComponent } from './pages/update-ad/update-ad.component';
import { NZ_I18N, en_US } from 'ng-zorro-antd/i18n';


@NgModule({
  declarations: [
    CompanyComponent,
    CompanyDashboardComponent,
    CreateAdComponent,
    AllAdsComponent,
    UpdateAdComponent
  ],
  imports: [
    CommonModule,
    CompanyRoutingModule,
    DemoNgZorroAntdModule,
    ReactiveFormsModule
  ],
  providers: [{provide: NZ_I18N , useValue: en_US}]
})
export class CompanyModule { }
