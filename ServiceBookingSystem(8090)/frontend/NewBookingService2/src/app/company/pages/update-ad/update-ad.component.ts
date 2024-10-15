import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CompanyService } from '../../services/company.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-update-ad',
  templateUrl: './update-ad.component.html',
  styleUrl: './update-ad.component.css'
})
export class UpdateAdComponent implements OnInit {

  selectedFile: File | null;
  imagePreview: string | ArrayBuffer | null;
  validateForm!: FormGroup;
  adId:any;
  existingImage: string | null = null;

  imgChanged=false;

  constructor(
    private activateRoute : ActivatedRoute,
    private companyService : CompanyService,
    private router : Router,
    private fb : FormBuilder
  ){}

  ngOnInit(): void {
    this.validateForm = this.fb.group({
      serviceName : [null , [Validators.required]],
      description : [null , [Validators.required]],
      price : [null , [Validators.required]]
    });

    this.getAdById();
  }


  onFileSelected(event : any){
    this.selectedFile = event.target.files[0];
    this.previewImage();
    this.existingImage = null;
    this.imgChanged=true;
  }

  previewImage(){

    const reader = new FileReader();
    reader.onload = ()=>{
      this.imagePreview = reader.result;
    }

    reader.readAsDataURL(this.selectedFile)

  }


  getAdById(){

    this.companyService.getAdsById(this.adId).subscribe(res => {
      console.log(res);
      this.validateForm.patchValue(res);
      this.existingImage = 'data:image/jpeg;base64'+res.returnedImg;
    })
  }

  updateAd(){

    const formData : FormData = new FormData();

    if(this.imgChanged && this.selectedFile){
      formData.append('img' ,this.selectedFile);
    }

    formData.append('serviceName' , this.validateForm.get('serviceName').value);
    formData.append('description' , this.validateForm.get('description').value);
    formData.append('price' , this.validateForm.get('price').value);

    this.companyService.updateAd(this.adId , formData).subscribe(res => {
      alert('Ad Updated Successfully');
      this.router.navigateByUrl('/company/ads');
    } , 
  error => {
    alert('Updation unsuccessfull');
  })

  }
}
