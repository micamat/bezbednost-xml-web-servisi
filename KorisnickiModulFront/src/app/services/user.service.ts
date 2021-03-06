import { Injectable } from '@angular/core';
import { Shared } from './Token';
import { SERVER_URL } from '../app.constant';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http:HttpClient,private tandu: Shared){}



  login(user:any){
    return this.http.post(SERVER_URL + '/auth/prijava',user,{responseType: 'text'});
  }

  registration(user:any){
    return this.http.post(SERVER_URL + '/auth/registerKorisnik',user,{responseType: 'text'});
  }

  changePass(user:any){
    return this.http.put(SERVER_URL + '/auth/changeKorisnik/'+ this.tandu.token,user,{responseType: 'text'});
  }
}
