import { Component } from '@angular/core';
import { CompanyService } from '../../services/company.service';

@Component({
  selector: 'app-all-ads',
  templateUrl: './all-ads.component.html',
  styleUrl: './all-ads.component.css'
})
export class AllAdsComponent {


  ads:any;

  constructor(private companyService : CompanyService ,){

  }

  ngOnInIt(){

    this.getAllAdsByUserId();

  }

  getAllAdsByUserId(){

    this.companyService.getAllAdsByUserId().subscribe(res => 
      this.ads = res
    )


  }


  updateImg(img){

    return 'data:image/jpeg;base64' + img ;

  }

  deleteAds(adId : any){
    this.companyService.deleteAd(adId).subscribe(res => {
      // write notification logic here 
      this.getAllAdsByUserId();

    })
  }


}
