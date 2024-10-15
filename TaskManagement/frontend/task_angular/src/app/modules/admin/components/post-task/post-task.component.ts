import { Component, OnInit } from '@angular/core';
import { AdminService } from '../../services/admin.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';

@Component({
  selector: 'app-post-task',
  templateUrl: './post-task.component.html',
  styleUrl: './post-task.component.css'
})
export class PostTaskComponent implements OnInit{

  taskForm !: FormGroup;
  listOfEmployees: any = [];
  listOfPriorities: any = ["LOW","MEDIUM","HIGH"];


  constructor(private adminService : AdminService ,
    private fb : FormBuilder,
    private snackbar : MatSnackBar,
    private route : Router
  ){}

  ngOnInit(): void {
    this.getUsers();

    this.taskForm = this.fb.group({
      employeeId : [null , [Validators.required]],
      title : [null , [Validators.required]],
      description : [null , [Validators.required]],
      dueDate : [null , [Validators.required]],
      priority : [null , [Validators.required]]
    })
  }

  getUsers() : void {
    this.adminService.getUsers().subscribe((res) => {
      this.listOfEmployees = res;
      console.log(res);
    })
  }


  postTask() {
    console.log(this.taskForm.value);

    this.adminService.postTask(this.taskForm.value).subscribe((res) => {
      if(res.id != null){
        this.snackbar.open("Task posted successfully" , "Close" , {duration:5000});
        this.route.navigateByUrl("/admin/dashboard");
      }else{
        this.snackbar.open("Something went wrong" , "ERROR" , {duration:5000});
      }
    })
  }

}
