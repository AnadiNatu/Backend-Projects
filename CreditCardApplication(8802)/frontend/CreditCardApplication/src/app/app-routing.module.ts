import { RouterModule, Routes } from "@angular/router";
import { HomePageComponent } from "./MyComponent/home-page/home-page.component";
import { NgModel } from "@angular/forms";
import { LoginPageComponent } from "./MyComponent/login-page/login-page.component";
import { RegisterPageComponent } from "./MyComponent/register-page/register-page.component";
import { CustomerprofileComponent } from "./MyComponent/customerprofile/customerprofile.component";
import { AdminprofileComponent } from "./MyComponent/adminprofile/adminprofile.component";
import { UpdateprofileComponent } from "./MyComponent/updateprofile/updateprofile.component";
import { NgModule } from "@angular/core";






const routes: Routes = [
    {path:'',component:HomePageComponent},
    {path:'login',component:LoginPageComponent},
    {path:'register',component:RegisterPageComponent},
    {path:'customerprofile/:id',component:CustomerprofileComponent},
    {path:'adminprofile',component:AdminprofileComponent},
    {path:'updateprofile/:id', component:UpdateprofileComponent}
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule{}