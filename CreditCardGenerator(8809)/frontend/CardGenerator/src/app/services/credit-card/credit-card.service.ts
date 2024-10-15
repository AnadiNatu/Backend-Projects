import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';


interface CustomerDto{
  name:string;
  bankname:string;
}

export interface CreditCardDto{
  cardNumber:string;
  expiryDate : Date;
  cvv:number;
  name:string;
  bankName:string;
}

const BASIC_URL = "http://localhost:8809/card";

@Injectable({
  providedIn: 'root'
})
export class CreditCardService {

  constructor(private http: HttpClient) { }

  generateCreditCard(customer : CustomerDto) : Observable<any>{

    return this.http.post<CreditCardDto>(BASIC_URL+`/generateCreditCard` , customer);

  }


  getCardDetails(name:string) : Observable<CreditCardDto>{

    return this.http.get<CreditCardDto>(BASIC_URL + `/getCardDetails/${name}`);

  }

  getAllCards() : Observable<CreditCardDto[]> {

    return this.http.get<CreditCardDto[]>(BASIC_URL + `/getAllCards`);

  }

  
}
