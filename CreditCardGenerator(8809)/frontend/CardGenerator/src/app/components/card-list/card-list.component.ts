import { Component, OnInit } from '@angular/core';
import { CreditCardDto, CreditCardService } from '../../services/credit-card/credit-card.service';

@Component({
  selector: 'app-card-list',
  templateUrl: './card-list.component.html',
  styleUrl: './card-list.component.css'
})
export class CardListComponent implements OnInit {

  creditCards : CreditCardDto[] = [];
  errorMessage: string = '';

  constructor(private creditCardService : CreditCardService){}

  ngOnInit(): void {
    this.getAllCards();
  }

  getAllCards():void {

    this.creditCardService.getAllCards().subscribe(
      (data) => {
        this.creditCards=data;
      },
      (error) => {
        this.errorMessage = 'Error fetching credit card details';
      }
    )

  }


}
