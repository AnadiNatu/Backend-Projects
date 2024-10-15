import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ClientService } from '../../services/client.service';
import { UserStorageService } from '../../../basic/services/storage/user-storage.service';

@Component({
  selector: 'app-ad-details',
  templateUrl: './ad-details.component.html',
  styleUrl: './ad-details.component.css'
})
export class AdDetailsComponent implements OnInit {

  adId = this.activateRoute.snapshot.params['adId'];
  avatarUrl:any;
  ad:any;

  validateForm!: FormGroup;

  constructor(
    private fb : FormBuilder,
    private activateRoute : ActivatedRoute,
    private router : Router,
    private clientService : ClientService
  ){}



  ngOnInit(): void {

    this.validateForm = this.fb.group({
      bookDate : [null , [Validators.required]]
    })

    this.getAdDetailsByAdId();
  }


  getAdDetailsByAdId(){

    this.clientService.getAdDetailsById(this.adId).subscribe(res => {
      console.log(res);
      this.avatarUrl = 'data:image/jpeg;base64' + res.adDTO.returnedImg;
      this.ad = res.adDto;
    })

  }

  bookService(){

    const bookServiceDto = {
      bookDate : this.validateForm.get(['bookDate']).value,
      adId : this.adId,
      userId : UserStorageService.getUserId()
    }

    this.clientService.bookService(bookServiceDto).subscribe(
      res=> {
        alert('Booking Successfull');
        this.router.navigateByUrl('/client/bookinds')
      }
    )
  }

}
