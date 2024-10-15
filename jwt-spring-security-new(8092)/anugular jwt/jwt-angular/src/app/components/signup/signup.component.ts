import { Component } from '@angular/core';
import { AuthService } from '../../service/auth.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css'
})
export class SignupComponent {

  signupForm: FormGroup;

  constructor(private service : AuthService , private fb : FormBuilder){}


  ngOnInIt(){

    this.signupForm = this.fb.group({
      name : ['' , Validators.required],
      email : ['' , Validators.required , Validators.email],
      password : ['' , Validators.required],
      confirmPassword : ['' , Validators.required]
    }, {validator: this.passwordMatchValidator})
  }

  private passwordMatchValidator( fg : FormGroup){

    const password = fg.get('password')?.value;
    const comfirmPassword = fg.get('comfirmPassword')?.value;

    if(password != comfirmPassword){

      fg.get("confirmPassword")?.setErrors({ passwordMismatch : true});
    } else {
      fg.get('confirmPassword')?.setErrors(null);
    }


  }


  signup(){

    console.log(this.signupForm?.value);

    this.service.signup(this.signupForm.value).subscribe((response) =>{
      console.log(response);
    })
  }
}
