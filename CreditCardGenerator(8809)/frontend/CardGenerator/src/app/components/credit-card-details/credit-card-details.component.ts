import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CreditCardDto, CreditCardService } from '../../services/credit-card/credit-card.service';

@Component({
  selector: 'app-credit-card-details',
  templateUrl: './credit-card-details.component.html',
  styleUrl: './credit-card-details.component.css'
})
export class CreditCardDetailsComponent implements OnInit{

  searchForm!: FormGroup;
  creditCard: CreditCardDto| null = null;
  errorMessage: string = '';

  constructor(private fb : FormBuilder , private creditCardService : CreditCardService ){}

  ngOnInit(): void {
    this.searchForm = this.fb.group({
      name : [null , [Validators.required]]
    });
  }


  submitForm():void{

    if(this.searchForm.valid){

      const name = this.searchForm.value.name;

      this.creditCardService.getCardDetails(name).subscribe(
        (data)=>{
          this.creditCard=data;
          this.errorMessage='';
        },
        (error) => {
          this.creditCard = null;
          this.errorMessage ='Error fetching Card Details';
        }
      )

    }

  }


}
