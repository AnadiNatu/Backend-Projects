import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth/auth.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { StorageService } from '../../services/storage/storage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  loginForm !: FormGroup;
  hidePassword = true;


  constructor(private fb : FormBuilder ,
    private authService : AuthService,
    private snackBar : MatSnackBar,
    private route : Router
  )
  {
    this.loginForm = this.fb.group({
      email : [null ,[Validators.required , Validators.email]],
      password : [null ,[Validators.required]],
    })

  }

  togglePasswordVisibility(){
    this.hidePassword = !this.hidePassword;
  }

  onSubmit(){
    console.log(this.loginForm.value);

    this.authService.login(this.loginForm.value).subscribe((res) => {
      console.log(res);
      if(res.id != null){
        const user = {
          id : res.userId,
          role : res.userRole
        }
        StorageService.saveUser(user);
        StorageService.saveToken(res.jwt);

        if(StorageService.isAdminLoggedIn()){
          this.route.navigateByUrl("/admin/dashboard");
        }else if(StorageService.isEmployeeLoggedIn()){
          this.route.navigateByUrl("/employee/dashboard");
        }
        this.snackBar.open("Login successful" , "Close" , {duration:5000});
      }else{
        this.snackBar.open("Invalid credentials" , "Close" , {duration : 5000 , panelClass:"error-snackbar"});
      }
    })
  }


}
