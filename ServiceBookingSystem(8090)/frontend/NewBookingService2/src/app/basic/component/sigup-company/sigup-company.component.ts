import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth/auth.service';

@Component({
  selector: 'app-sigup-company',
  templateUrl: './sigup-company.component.html',
  styleUrl: './sigup-company.component.css'
})
export class SigupCompanyComponent {

  validateForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private authService : AuthService,
    private router : Router
  ){}


  ngOnInit(): void {
    this.validateForm = this.fb.group({
      email: [null , [Validators.email , Validators.required]],
      name: [null , [Validators.required]],
      address: [null , [Validators.required]],
      phone: [null ],
      password: [null , [Validators.required]],
      checkPassword: [null , [Validators.required]]
    });
  }


  submitForm(): void{

    this.authService.registerCompany(this.validateForm.value).subscribe(
      res => {
        alert('SignUp Successful');
        this.router.navigateByUrl('/login')
      },
      error => {
        alert(`Error : ${error.error}`);
      }
    );
  }

}
