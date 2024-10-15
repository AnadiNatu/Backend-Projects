import { Component } from '@angular/core';
import { ClientService } from '../../services/client.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-client-dashboard',
  templateUrl: './client-dashboard.component.html',
  styleUrl: './client-dashboard.component.css'
})
export class ClientDashboardComponent {

  ads : any = [];

  validateForm! : FormGroup;

  constructor( private clienttService : ClientService , private fb : FormBuilder){

  }

  getAllAds(){

    this.clienttService.getAllAds().subscribe(res => {

      this.ads = res;

    })

  }

  updateImg(img){

    return 'data:image/jpeg;base64' + img;

  }

  searchAdByName(){

    this.clienttService.searchAdsByName(this.validateForm.get(['service']).value).subscribe(res => {
      this.ads = res;
    })

  }

  ngOnInIt(){

    this.validateForm = this.fb.group({
      service : [null , [Validators.required]]
    })
    this.getAllAds();

  }


}
