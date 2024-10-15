import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { CompanyService } from '../../services/company.service';

@Component({
  selector: 'app-create-ad',
  templateUrl: './create-ad.component.html',
  styleUrl: './create-ad.component.css'
})
export class CreateAdComponent {


  selectedFile : File | null;
  imagePreview : string | ArrayBuffer | null;
  validateForm : FormGroup;

  // Notification in constructor
  constructor(private fb : FormBuilder , private notification : Notification , private router : Router , private companyService : CompanyService){


  }

  ngOnInIt(){

    // See the video
  }




  onFileSelected(event:any){

    this.selectedFile = event.target.files[0];
    this.previewImage();

  }

  previewImage(){

    const reader = new FileReader();

    reader.onload = () =>{

      this.imagePreview = reader.result;
    }

    reader.readAsDataURL(this.selectedFile);

  }


  postAds(){

    const formData : FormData = new FormData();

    formData.append('img' , this.selectedFile);
    formData.append('serviceName' , this.validateForm.get('serviceName').value);
    formData.append('description' , this.validateForm.get('description').value);
    formData.append('price' , this.validateForm.get('price').value);

    this.companyService.postAd(formData).subscribe(

      res => {
        // notification code , see the video
        this.router.navigateByUrl('/company/ads')
      }
    )




  }


}
