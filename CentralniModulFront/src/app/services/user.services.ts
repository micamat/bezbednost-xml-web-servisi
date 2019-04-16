import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SERVER_URL } from '../constant';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http:HttpClient) { }

  tekst(){
    return this.http.get(SERVER_URL + '/user/tekst', {responseType: 'text'});
  }
  
}
