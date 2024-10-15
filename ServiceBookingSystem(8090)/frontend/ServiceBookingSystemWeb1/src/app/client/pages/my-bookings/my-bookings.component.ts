import { Component } from '@angular/core';
import { ClientService } from '../../services/client.service';

@Component({
  selector: 'app-my-bookings',
  templateUrl: './my-bookings.component.html',
  styleUrl: './my-bookings.component.css'
})
export class MyBookingsComponent {


  bookedServices:any;

  constructor(private clientService : ClientService){

  }

  ngOnInIt(){
    this.getMyBookings();
  }

  getMyBookings(){

    this.clientService.getMyBookings().subscribe(res =>{
      this.bookedServices = res;
    })
  }
}
