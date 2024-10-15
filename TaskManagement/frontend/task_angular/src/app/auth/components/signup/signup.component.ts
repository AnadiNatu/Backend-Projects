import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth/auth.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css'
})
export class SignupComponent implements OnInit {


  signupForm !: FormGroup;
  hidePassword = true;


  constructor(private fb : FormBuilder  , 
    private authService : AuthService,
    private snackBar : MatSnackBar,
  private router : Router){
    }
      
    
  
  
    

  
  ngOnInit(): void {
    this.signupForm = this.fb.group({
      name : [null ,[Validators.required]],
      email : [null ,[Validators.required , Validators.email]],
      password : [null ,[Validators.required]],
      confirmPassword : [null ,[Validators.required]],
    })
  }

  togglePasswordVisibility(){
    this.hidePassword = !this.hidePassword;
  }

  onSubmit(){
    console.log(this.signupForm.value);


    const password = this.signupForm.get("pasword")?.value;
    const confirmPassword = this.signupForm.get("confirmPassword")?.value;

    if(confirmPassword !== password ){
      this.snackBar.open("Password do not match" , "Close" , {duration : 5000 , panelClass: "error-snackbar"});
      return;
    }

    this.authService.signup(this.signupForm.value).subscribe((res) => {
      console.log(res);
      if(res.id !=null){
        this.snackBar.open("Signup successful ", "Close" , {duration:5000});
        this.router.navigateByUrl("/login");
      }else{
        this.snackBar.open("SignUp failed" , "Close" , {duration:5000 , panelClass:"error-snackbar"});
      }
    })

  }

}
