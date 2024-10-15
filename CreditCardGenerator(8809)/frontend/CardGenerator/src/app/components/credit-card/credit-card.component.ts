import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { CreditCardService } from '../../services/credit-card/credit-card.service';
import { OnInit } from '@angular/core';
import { Validators } from '@angular/forms';

@Component({
  selector: 'app-credit-card',
  templateUrl: './credit-card.component.html',
  styleUrl: './credit-card.component.css'
})
export class CreditCardComponent implements OnInit {

  validateForm !: FormGroup;
  creditCard :any;
  errorMessage :string = '';


  constructor(private fb: FormBuilder , private creditCardService : CreditCardService){}

  ngOnInit(): void {
    this.validateForm = this.fb.group({
      name: [null ,[Validators.required]],
      bankName: [null ,[Validators.required]]
    });
  }


  submitForm() : void {

    if(this.validateForm.valid){

      const customer = this.validateForm.value;

      this.creditCardService.generateCreditCard(customer).subscribe(
        (data) => {
          this.creditCard = data;
          this.errorMessage = '';
        },
        (error) => {
          this.creditCard = null;
          this.errorMessage = 'Error generating credit card';
        }
      )
    }

  }
}
