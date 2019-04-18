import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { SERVER_URL } from '../main.constant';
import { text } from '@angular/core/src/render3';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http:HttpClient) { }
  
  login(user){
    let httpParams = new HttpParams().append("email", user.email).append("password", user.password);

    console.log(user.email);
    console.log(httpParams);
    return this.http.post(SERVER_URL+'/login/login', {}, {responseType: 'text', params: httpParams});
  } 
}
