import { Component } from '@angular/core';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { ClientService } from '../../services/client.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserStorageService } from '../../../basic/services/storage/user-storage.service';

@Component({
  selector: 'app-review',
  templateUrl: './review.component.html',
  styleUrl: './review.component.css'
})
export class ReviewComponent {


  bookId : number = this.activateroute.snapshot.params['id'];
  validateForm : FormGroup;

  constructor(private fb : FormBuilder , private router : Router , private clientService : ClientService , private activateroute : ActivatedRoute){
    // add notification dependency
  }


  ngOnInIt(){

    this.validateForm = this.fb.group({
      rating : [null , Validators.required],
      review : [null , Validators.required],
    })

  }

  giveReview(){

    const reviewDto = {
      rating : this.validateForm.get("rating").value,
      review : this.validateForm.get("rating").value,
      userId : UserStorageService.getUserId,
      bookId : this.bookId
    }

    this.clientService.giveReview(reviewDto).subscribe( res => {
      //notification logic 
      this.router.navigateByUrl("/client/booking");
    }, //error notification
  )
  }

}
