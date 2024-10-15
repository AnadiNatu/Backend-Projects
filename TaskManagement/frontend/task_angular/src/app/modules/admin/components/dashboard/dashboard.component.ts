import { Component, OnInit } from '@angular/core';
import { AdminService } from '../../services/admin.service';
import { R } from '@angular/cdk/keycodes';
import { MatSnackBar } from '@angular/material/snack-bar';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent implements OnInit{

  listOfTasks : any = [];
  searchForm!: FormGroup;

  constructor(private adminService : AdminService,
    private snackbar : MatSnackBar ,
    private fb : FormBuilder
    
  ){}

  ngOnInit(): void {
    this.getTasks();

    this.searchForm = this.fb.group({
      title : [ null ,[Validators.required]]
    })
  }

  getTasks(){
    this.adminService.getAllTask().subscribe((res) => {
      this.listOfTasks = res;
    })
  }

  deleteTask(id : number){
    this.adminService.deleteTask(id).subscribe((res) => {

      this.snackbar.open("Task Deleted Successfully" ,"Close" , {duration : 5000});
      this.getTasks();
    })
  }


  searchTask(){
    this.listOfTasks = [];

    const title = this.searchForm.get('title')!.value;

    console.log(title);

    this.adminService.searchTask(title).subscribe( (res) => {

      console.log(res);
      this.listOfTasks=res;
      this.snackbar.open("Search Successfull" , "Close" , {duration: 5000});
    })

  }
}
