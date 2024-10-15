import { Component, OnInit } from '@angular/core';
import { StorageService } from './auth/services/storage/storage.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit{

  isEmployeeLoggenIn: boolean = StorageService.isEmployeeLoggedIn();
  isAdminLoggenIn: boolean = StorageService.isAdminLoggedIn();


  constructor(private route : Router){}

  ngOnInit(): void {
    this.route.events.subscribe((events) => {
      this.isAdminLoggenIn = StorageService.isAdminLoggedIn();
      this.isEmployeeLoggenIn = StorageService.isEmployeeLoggedIn();
    })
  }


  logout() : void{

    StorageService.logout();
    this.route.navigateByUrl("/login");
  }


}
