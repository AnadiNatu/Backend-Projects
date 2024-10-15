import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { StorageService } from '../../../auth/services/storage/storage.service';

const BASIC_URL = "http://localhost:8080/";

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {



  constructor(private http : HttpClient) { }

  getTaskByEmployeeId() : Observable<any>{
    return this.http.get(BASIC_URL + `api/employee/tasks` , {
      headers:this.createAuthorizationHeader()
    });
  }

  updateStatus(id : number , status : string) : Observable<any>{
    return this.http.get(BASIC_URL + `api/employee/task/${id}/${status}` , {
      headers : this.createAuthorizationHeader()
    })
  }

  getTaskById(id:number) : Observable<any>{
    return this.http.get(BASIC_URL + "api/employee/task/" + id , {
      headers : this.createAuthorizationHeader()
    })
    }
  

  createComment(id : number , comment : string ) : Observable<any>{

    const params = {
      content : comment
    }
    // this how we end a param in a url;

    return this.http.post(BASIC_URL + `api/employee/task/comment/` + id ,null,{
      params : params,
      headers : this.createAuthorizationHeader()
    })

  }

  getCommentsByTaskId(id : number) : Observable<any> {

    return this.http.get(BASIC_URL + `api/employee/comments/`+id ,{
      headers : this.createAuthorizationHeader()
    })
  }


  private createAuthorizationHeader() : HttpHeaders{

    return new HttpHeaders().set(
      'Authorization' , 'Bearer ' + StorageService.getToken()
    );
    
  }
}
