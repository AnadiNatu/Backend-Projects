import { Component } from '@angular/core';
import { CompanyService } from '../../services/company.service';

@Component({
  selector: 'app-company-dashboard',
  templateUrl: './company-dashboard.component.html',
  styleUrl: './company-dashboard.component.css'
})
export class CompanyDashboardComponent {

  bookings : any;

  constructor(private companyService : CompanyService ,){

  }


  getAllAdBookings(){

    this.companyService.getAllAdBooking().subscribe(res => {
      console.log(res);
      this.bookings = res;
    })

  }

  changeBookingStatus(bookingId : number ,  status : string){
    this.companyService.changeBookingStatus(bookingId,status).subscribe(res =>{
      // notification logic
      this.getAllAdBookings();
    } ,
    // error notification
  )
  }



  ngOnInIt(){
    this.getAllAdBookings();
  }

}
