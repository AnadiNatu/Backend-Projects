import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import { UserStorageService } from '../storage/user-storage.service';

const BASIC_URL= 'http://localhost:8080/'
export const AUTH_HEADER = 'authorization'


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http :HttpClient, private userStorageService : UserStorageService) { }



  registerClient(singupRequestDto:any) : Observable<any>{
    return this.http.post(BASIC_URL + "client/signup" , singupRequestDto);
  }

  registerCompanys(singupRequestDto:any) : Observable<any>{
    return this.http.post(BASIC_URL + "company/signup" , singupRequestDto);
  }

  login(username:string , password:string){
    return this.http.post(BASIC_URL + "authenticate" , {username,password} , {observe:'response'})
    .pipe(
      map((res : HttpResponse<any>) => {
        console.log(res.body)
        this.userStorageService.saveUser(res.body);
      const tokenLenght = res.headers.get(AUTH_HEADER)?.length;
      const bearerToken = res.headers.get(AUTH_HEADER)?.substring(7 , tokenLenght);
      console.log(bearerToken);
      this.userStorageService.saveToken(bearerToken);

      return res;

      }
      )
    )
  }


}
