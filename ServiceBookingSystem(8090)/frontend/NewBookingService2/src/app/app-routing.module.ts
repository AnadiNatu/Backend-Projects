import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './basic/component/login/login.component';
import { SignupComponent } from './basic/component/signup/signup.component';
import { SigupClientComponent } from './basic/component/sigup-client/sigup-client.component';
import { SigupCompanyComponent } from './basic/component/sigup-company/sigup-company.component';

const routes: Routes = [{ path: 'company', loadChildren: () => import('./company/company.module').then(m => m.CompanyModule) }, 
                        { path: 'client', loadChildren: () => import('./client/client.module').then(m => m.ClientModule) },
                        { path: 'register_client' , component: SigupClientComponent},
                        { path: 'register_company' , component: SigupCompanyComponent},
                        { path: 'login' , component: LoginComponent},
                        { path: 'register' , component: SignupComponent}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
