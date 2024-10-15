import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { EmployeeService } from '../../services/employee.service';

@Component({
  selector: 'app-view-task-details',
  templateUrl: './view-task-details.component.html',
  styleUrl: './view-task-details.component.css'
})
export class ViewTaskDetailsComponent {

  taskId : number = this.activateRoute.snapshot.params["id"];

  taskData : any;

  comments : any;

  commentForm !: FormGroup;

  constructor(private adminService : EmployeeService , 
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
