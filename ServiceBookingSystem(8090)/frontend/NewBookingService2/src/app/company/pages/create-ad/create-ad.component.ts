import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CompanyService } from '../../services/company.service';

@Component({
  selector: 'app-create-ad',
  templateUrl: './create-ad.component.html',
  styleUrl: './create-ad.component.css'
})
export class CreateAdComponent implements OnInit {

  
  selectedFile : File | null;
  imagePreview : string | ArrayBuffer | null;
  validateForm!: FormGroup;


  constructor(
    private router : Router ,
    private fb : FormBuilder,
    private companyService : CompanyService
  ){}



  ngOnInit(): void {
    this.validateForm = this.fb.group({
      serviceName : [null,[Validators.required]],
      description : [null , [Validators.required]],
      price : [null , [Validators.required]],
    })
  }

  onFileSelected(event : any){

    this.selectedFile = event.target.files[0];
    this.previewImage();

  }

  previewImage(){

    const reader = new FileReader();

    reader.onload = () => {
      this.imagePreview = reader.result
    }

    reader.readAsDataURL(this.selectedFile);
  }


  postAd() {

    const formData : FormData = new FormData();

    formData.append('img' , this.selectedFile);
    formData.append('serviceName', this.validateForm.get('serviceName').value);
    formData.append('description' , this.validateForm.get('description').value);
    formData.append('price' , this.validateForm.get('price').value);


    this.companyService.postAd(formData).subscribe(res => {
      alert(' Ad Posted Successfully');
      this.router.navigateByUrl('/company/ads');
    } ,
  errors => {
    alert('Ad Not Posted')
  })
  }



}
