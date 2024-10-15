import { Component, OnInit } from '@angular/core';
import { CompanyService } from '../../services/company.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-all-ads',
  templateUrl: './all-ads.component.html',
  styleUrl: './all-ads.component.css'
})
export class AllAdsComponent implements OnInit{

  ads : any ;

  constructor(private companyService : CompanyService , private router : Router){
    
  }


  ngOnInit(): void {
    this.getAllAdsByUserId();
  }



  getAllAdsByUserId(): void {

    this.companyService.getAllAdsByUserId().subscribe(res => {
      this.ads = res;
    })
  }


  updateImg(img : string):string {

    return 'data:image/jpeg;base64' + img;

  }


  deleteAd(adId : any):void {

    if(confirm('Are you sure you want to delete this ad?')){
      this.companyService.deleteAd(adId).subscribe( () =>{
        alert('Ad Delete Successfully');
        this.getAllAdsByUserId();
      });
    }
  }


  
}
