import { Component, OnInit } from '@angular/core';
import { AdminService } from '../../services/admin.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-update-task',
  templateUrl: './update-task.component.html',
  styleUrl: './update-task.component.css'
})
export class UpdateTaskComponent implements OnInit{

  id : number = this.activateRoute.snapshot.params["id"];

  updateTaskForm !: FormGroup;
  listOfEmployees: any = [];
  listOfPriorities: any = ["LOW","MEDIUM","HIGH"];
  listOfTaskStatus: any = ["PENDING" ,"IN-PROGRESS", "COMPLETED" ,"DEFFERED" ,"CANCELLED"];



  constructor(private adminService : AdminService , private activateRoute : ActivatedRoute
    , private fb : FormBuilder , private snackbar : MatSnackBar , private route : Router)
    {}
  
  
    ngOnInit(): void {
      this.getTaskById();

      this.getUsers();

      this.updateTaskForm = this.fb.group({
        employeeId : [null , [Validators.required]],
        title : [null , [Validators.required]],
        description : [null , [Validators.required]],
        dueDate : [null , [Validators.required]],
        priority : [null , [Validators.required]],
        taskPriority : [null , [Validators.required]],

      })
    }
  
  
  getTaskById(){

    this.adminService.getTaskById(this.id).subscribe( (res) => {
      this.updateTaskForm.patchValue(res);
      console.log(res);
    })
    
  }


  getUsers() : void {
    this.adminService.getUsers().subscribe((res) => {
      this.listOfEmployees = res;
      console.log(res);
    })
  }

  updateTask() {
    console.log(this.updateTaskForm.value);

    this.adminService.updateTask(this.id,this.updateTaskForm.value).subscribe((res) => {
      if(res.id != null){
        this.snackbar.open("Task Updated successfully" , "Close" , {duration:5000});
        this.route.navigateByUrl("/admin/dashboard");
      }else{
        this.snackbar.open("Something went wrong" , "ERROR" , {duration:5000});
      }
    })
  }


}
