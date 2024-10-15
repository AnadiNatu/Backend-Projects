import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserStorageService } from '../../basic/services/storage/user-storage.service';


const BASIC_URL=  "http://localhost:8090";

@Injectable({
  providedIn: 'root'
})
export class ClientService {



  constructor(private http : HttpClient) { }

  getAllAds(): Observable<any>{

    const userId = UserStorageService.getUserId();

    return this.http.get(BASIC_URL + `api/client/ads` , {
      headers:this.createAuthorizationHeader()
    });
  }

  searchAdByName(name : any) : Observable<any> {

    return this.http.get(BASIC_URL + `api/client/search/${name}`,{
      headers : this.createAuthorizationHeader()
    });
  }

  getAdDetailsById(adId : any): Observable<any> {

    return this.http.get(BASIC_URL+`api/client/ad/${adId}` , {
      headers : this.createAuthorizationHeader()
    });
  }


  bookService(bookDto:any):Observable<any>{

    return this.http.post(BASIC_URL + `api/client/book-service`, bookDto ,{
      headers : this.createAuthorizationHeader()
    });
  }

  getMyBookings():Observable<any>{

    const userId = UserStorageService.getUserId();

    return this.http.get(BASIC_URL + `api/client/my-bookings/${userId}` , {
      headers : this.createAuthorizationHeader()
    });
  }

  giveReview(reviewDto : any): Observable<any>{

    return this.http.post(BASIC_URL + `api/client/review` , reviewDto , {
      headers : this.createAuthorizationHeader()
    });
  }

  createAuthorizationHeader(): HttpHeaders{

    let authHeader : HttpHeaders = new HttpHeaders();

    return authHeader.set(
      'Authorization',
      'Bearer '+ UserStorageService.getToken()
    )
  }


}
