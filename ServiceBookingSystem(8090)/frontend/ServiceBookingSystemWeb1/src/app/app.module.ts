import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './basic/component/login/login.component';
import { SignupComponent } from './basic/component/signup/signup.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { DemoNgZorroAntdModule } from './DemoNgZorroAntdModule';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NZ_I18N, en_US } from 'ng-zorro-antd/i18n';
import { SignupClientComponent } from './basic/component/signup-client/signup-client.component';
import { SignupCompanyComponent } from './basic/component/signup-company/signup-company.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SignupComponent,
    SignupClientComponent,
    SignupCompanyComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    DemoNgZorroAntdModule,
    ReactiveFormsModule
  ],
  providers: [{provide: NZ_I18N , useValue: en_US}],
  bootstrap: [AppComponent]
})
export class AppModule { }
