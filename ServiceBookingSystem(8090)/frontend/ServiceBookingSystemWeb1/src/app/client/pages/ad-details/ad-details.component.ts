import { Component } from '@angular/core';
import { ClientService } from '../../services/client.service';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { UserStorageService } from '../../../basic/services/storage/user-storage.service';

@Component({
  selector: 'app-ad-details',
  templateUrl: './ad-details.component.html',
  styleUrl: './ad-details.component.css'
})
export class AdDetailsComponent {

  adId = this.activateRoute.snapshot.params['adId'];
  avatarUrl:any ;
  ad:any;

  validateForm! : FormGroup ;

  constructor(private clientService: ClientService , 
    private activateRoute : ActivatedRoute,
    private router : Router,
    private notification : NzNotificationService,
    private fb : FormBuilder){}

  
  ngOnInIt(){

    this.validateForm = this.fb.group({
      bookDate : [null ,[Validators.required]]
    })

    this.getAdDetailsByAdId();
  }

  getAdDetailsByAdId(){
    this.clientService.getAdDetailsByAdId(this.adId).subscribe(res => {
      console.log(res);
      this.avatarUrl='data:image/jpeg;base64' + res.adDTO.returnedImg;
      this.ad = res.adDTO;
    })
  }

  bookService(){
    const bookServiceDto = {
      bookDate : this.validateForm.get(['bookDate']).value,
      adId : this.adId,
      userId : UserStorageService.getUserId()
    }

    this.clientService.bookService(bookServiceDto).subscribe(res => {
      this.notification
      .success(
        'SUCCESS',
        'Request Posted Successfully',
        {nzDuration : 5000}
      );

      this.router.navigateByUrl('/client/bookings');
    })
  }

}
