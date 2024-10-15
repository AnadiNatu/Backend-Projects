import { Component, OnInit } from '@angular/core';
import { AdminService } from '../../services/admin.service';
import { ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-view-task-details',
  templateUrl: './view-task-details.component.html',
  styleUrl: './view-task-details.component.css'
})
export class ViewTaskDetailsComponent implements OnInit {

  taskId : number = this.activateRoute.snapshot.params["id"];

  taskData : any;

  comments : any;

  commentForm !: FormGroup;

  constructor(private adminService : AdminService , 
    private activateRoute : ActivatedRoute ,
    private fb : FormBuilder ,
  private snackbar : MatSnackBar){}

    ngOnInit(): void {
      this.getTaskById();
      this.getCommentByTaskId();
      this.commentForm = this.fb.group({
        content : [null , [Validators.required]]
      })
    }


    getTaskById() {
      this.adminService.getTaskById(this.taskId).subscribe( (res) => {
        this.taskData = res;
      })
    }

    getCommentByTaskId(){
      this.adminService.getCommentsByTaskId(this.taskId).subscribe( (res) => {
      this.comments = res;
      })
    }


    publishComment(){
      this.adminService.createComment(this.taskId , this.commentForm.get("cotent")?.value).subscribe( (res) => {
        if(res.id != null){
          this.snackbar.open("Comment potd Successfully" , "Close" , {duration : 5000});
          this.getCommentByTaskId();
          
        }else{
          this.snackbar.open("Something Went Wrong" , "Close" , {duration : 5000});
        }
      })
    }



}
