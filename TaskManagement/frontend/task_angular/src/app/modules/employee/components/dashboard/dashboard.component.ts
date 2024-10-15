import { Component, OnInit } from '@angular/core';
import { EmployeeService } from '../../services/employee.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent  implements OnInit{

  listOfTasks : any = [];

  constructor(private employeeService : EmployeeService,
    private snackbar : MatSnackBar
  ){}

  ngOnInit(): void {
    this.getTasks();
  }

  getTasks(){
    this.employeeService.getTaskByEmployeeId().subscribe((res) => {
      console.log(res);
      this.listOfTasks=res;
    });
  }


  updateStatus(id , status){
    this.employeeService.updateStatus(id,status).subscribe( res => {
      if(res.id != null){
        this.snackbar.open("Task Updated Successfully" , "Close" , {duration : 5000});
       
        this.getTasks();
      }else{
        this.snackbar.open("Error while Uupdating task" , "ERROR" , {duration : 5000});
      }
    })
  }

}
