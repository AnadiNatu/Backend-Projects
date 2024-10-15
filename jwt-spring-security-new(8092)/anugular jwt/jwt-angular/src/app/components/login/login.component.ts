import { Component } from '@angular/core';
import { AuthService } from '../../service/auth.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  loginForm : FormGroup;

  constructor(private service : AuthService , private fb : FormBuilder , private router: Router){}

  ngOnInIt(){

    this.loginForm = this.fb.group({
      email: ['' , Validators.email, Validators.required],
      password: ['' , Validators.required],
    })
  }

  login(){
    console.log(this.loginForm.value);

    this.service.login(this.loginForm.value).subscribe((response) =>{
     console.log(response);
     if(response.jwtToken){
      alert(response.jwtToken);

      const jwtToken = response.jwtToken;
      localStorage.setItem('JWT',jwtToken);

      this.router.navigateByUrl('/dashboard');
     }
    })
  }

}
