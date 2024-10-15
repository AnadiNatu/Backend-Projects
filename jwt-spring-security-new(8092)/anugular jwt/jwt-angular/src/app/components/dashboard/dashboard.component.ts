import { Component } from '@angular/core';
import { AuthService } from '../../service/auth.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {

  message:String;

  constructor(private service : AuthService){}


  ngOnInIt(){

    this.hello();

  }

  hello(){
    this.service.hello().subscribe((response)=>{
      console.log(response);
      this.message = response.message;
    })
  }
}
