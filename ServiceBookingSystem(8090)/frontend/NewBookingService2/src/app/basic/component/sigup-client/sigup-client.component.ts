import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth/auth.service';

@Component({
  selector: 'app-sigup-client',
  templateUrl: './sigup-client.component.html',
  styleUrl: './sigup-client.component.css'
})
export class SigupClientComponent {

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
      lastName: [null , [Validators.required]],
      phone: [null ],
      password: [null , [Validators.required]],
      checkPassword: [null , [Validators.required]]
    });
  }


  submitForm(): void{

    this.authService.registerClient(this.validateForm.value).subscribe(
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
