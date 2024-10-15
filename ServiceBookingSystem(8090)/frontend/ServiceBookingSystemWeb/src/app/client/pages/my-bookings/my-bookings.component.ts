import { Component } from '@angular/core';
import { ClientService } from '../../services/client.service';

@Component({
  selector: 'app-my-bookings',
  templateUrl: './my-bookings.component.html',
  styleUrl: './my-bookings.component.css'
})
export class MyBookingsComponent {

  bookingService : any;

  constructor(private clientService : ClientService){

  }


  ngOnInIt(){

  }

  getMyBookings(){

    this.clientService.getMyBooking().subscribe(res =>{
      this.bookingService = res;
    } )

  }
  
}
