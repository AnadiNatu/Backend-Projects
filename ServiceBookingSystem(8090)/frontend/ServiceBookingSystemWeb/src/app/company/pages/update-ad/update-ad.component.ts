import { Component } from '@angular/core';
import { CompanyComponent } from '../../company.component';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup } from '@angular/forms';
import { CompanyService } from '../../services/company.service';

@Component({
  selector: 'app-update-ad',
  templateUrl: './update-ad.component.html',
  styleUrl: './update-ad.component.css'
})
export class UpdateAdComponent {


  adId: any = this.activateRoute.snapshot.params['id'];


  selectedFile: File | null;
  imagePreview: string | ArrayBuffer | null;
  validateForm: FormGroup;
  existingImage: string | null = null;

  imgChanged = false;

  // Notification in constructor
  constructor(private fb: FormBuilder, private notification: Notification, private router: Router, private activateRoute: ActivatedRoute, private companyService: CompanyService) {


  }

  ngOnInIt() {

    // See the video
    this.getAdById();
  }




  onFileSelected(event: any) {

    this.selectedFile = event.target.files[0];
    this.previewImage();
    
    this.existingImage = null;
    this.imgChanged = true;
  }

  previewImage() {

    const reader = new FileReader();

    reader.onload = () => {

      this.imagePreview = reader.result;
    }

    reader.readAsDataURL(this.selectedFile);

  }


  updateAds() {

    const formData: FormData = new FormData();

    if (this.imgChanged && this.selectedFile) {
      formData.append('img', this.selectedFile);
    }

    formData.append('serviceName', this.validateForm.get('serviceName').value);
    formData.append('description', this.validateForm.get('description').value);
    formData.append('price', this.validateForm.get('price').value);

    this.companyService.updateAd(this.adId , formData).subscribe(

      res => {
        // notification code , see the video
        this.router.navigateByUrl('/company/ads')
      }
    )

    this.getAdById();

  }

  getAdById() {

    this.companyService['getAdsById'](this.adId).subscribe(
      res => {
        console.log(res);
        this.validateForm.patchValue(res);
        this.existingImage = 'data:image/jpeg;base64,' + res.returnedImg;
      })
  }



}
