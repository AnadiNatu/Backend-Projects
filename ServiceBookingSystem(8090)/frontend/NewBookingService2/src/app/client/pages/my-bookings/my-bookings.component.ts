import { Component, OnInit } from '@angular/core';
import { ClientService } from '../../services/client.service';

@Component({
  selector: 'app-my-bookings',
  templateUrl: './my-bookings.component.html',
  styleUrl: './my-bookings.component.css'
})
export class MyBookingsComponent implements OnInit {

  bookedService:any;

  constructor(private clientService : ClientService){

  }

  ngOnInit(): void {
    this.getMyBookings();
  }

  getMyBookings(){

    this.clientService.getMyBookings().subscribe(res => {
      this.bookedService=res;
    })
  }


}
