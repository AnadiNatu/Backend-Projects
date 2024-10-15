import { Component } from '@angular/core';
import { UserStorageService } from './basic/services/storage/user-storage.service';
import { Route, Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'ServiceBookingSystemWeb';

  isClientLoggedIn : boolean = UserStorageService.isClientLoggedIn();
  isCompanyLoggedIn : boolean = UserStorageService.isCompanyLoggedIn();

  constructor(private router : Router){

  }

  ngOninIt(){
    this.router.events.subscribe(event =>{
      this.isClientLoggedIn = UserStorageService.isClientLoggedIn();
      this.isCompanyLoggedIn = UserStorageService.isCompanyLoggedIn();
    }
    )
  }

  logout(){
    UserStorageService.signOut();
    this.router.navigateByUrl('login');
  }
}
