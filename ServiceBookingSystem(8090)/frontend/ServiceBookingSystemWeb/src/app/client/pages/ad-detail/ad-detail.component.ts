import { Component } from '@angular/core';
import { ClientComponent } from '../../client.component';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { ClientService } from '../../services/client.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserStorageService } from '../../../basic/services/storage/user-storage.service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-ad-detail',
  templateUrl: './ad-detail.component.html',
  styleUrl: './ad-detail.component.css'
})
export class AdDetailComponent {

  adId = this.activatedroute.snapshot.params['adId'];

  avatarUrl : any;

  ad : any;

  validateForm! : FormGroup;

  reviews : any;

  constructor( private clientService : ClientService, private activatedroute : ActivatedRoute , private fb : FormBuilder , private router : Router){

  }

  getAdDetailByAdId(){

    this.clientService.getAdDetailsById(this.adId).subscribe(res =>{
      console.log(res);
      this.avatarUrl = " data:image/jpeg;base64" + res.adDto.returnedImg;
      this.ad = res.adDto;
      this.reviews = res.reviewDtoList;
    })
  
  }



  bookService(){

    const bookServiceDTO = {
      bookDate : this.validateForm.get(['bookDate']).value,
      adId : this.adId,
      userId : UserStorageService.getUserId
    }

    this.clientService.bookService(bookServiceDTO).subscribe(res => [
      // notification logic

      this.router.navigateByUrl('/client/bookings')
    ])



  }


  ngOnInIt(){

   this.validateForm = this.fb.group({
    bookDate : [null , [Validators.required]]
   })

   this.getAdDetailByAdId(); 
  }



}
