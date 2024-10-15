import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ClientService } from '../../services/client.service';

@Component({
  selector: 'app-client-dashboard',
  templateUrl: './client-dashboard.component.html',
  styleUrl: './client-dashboard.component.css'
})
export class ClientDashboardComponent implements OnInit{

  ads:any;
  validateForm!:FormGroup;

  constructor(private clientService : ClientService , private fb : FormBuilder){}

  getAllAds(){

    this.clientService.getAllAds().subscribe(res => {
      this.ads=res;
    })

  }

  updateImg(img){
    return 'data:image/jpeg;base64'+img;
  }


  searchAdByName(){

    this.clientService.searchAdByName(this.validateForm.get(['service']).value).subscribe(res => {
      this.ads=res;
    })
  }

  ngOnInit(): void {
    this.validateForm = this.fb.group({
      service: [null , [Validators.required]]
    })
    this.getAllAds();
  }

  

}
