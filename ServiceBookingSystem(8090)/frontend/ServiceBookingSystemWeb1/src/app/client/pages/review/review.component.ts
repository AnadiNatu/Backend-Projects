import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { ClientService } from '../../services/client.service';
import { ActivatedRoute, Router } from '@angular/router';
import { UserStorageService } from '../../../basic/services/storage/user-storage.service';

@Component({
  selector: 'app-review',
  templateUrl: './review.component.html',
  styleUrl: './review.component.css'
})
export class ReviewComponent {

  bookId: number = this.activateRoute.snapshot.params['id'];
  validateForm! : FormGroup;

  constructor(private fb : FormBuilder,
    private notification : NzNotificationService,
    private router : Router ,
    private clientService : ClientService,
    private activateRoute : ActivatedRoute
  ){}

  ngOnInIt(){

    this.validateForm = this.fb.group({
      rating: [null , Validators.required],
      review: [null , Validators.required]
    })
  }

  giveReview(){

    const reviewDto = {
      rating: this.validateForm.get("rating").value,
      review: this.validateForm.get("review").value,
      userId: UserStorageService.getUserId(),
      bookId : this.bookId
    }

    this.clientService.giveReview(reviewDto).subscribe(res => {
      this.notification
      .success(
        'SUCCESS',
        'Request Posted Successfully',
        {nzDuration : 5000}
      );
    } , error =>{
      this.notification
      .error(
        'ERROR',
        'Request Posted Successfully',
        {nzDuration : 5000}
      );
    })
  }


}
