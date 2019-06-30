import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Shared } from './Token';
import { SERVER_URL } from '../app.constant';

@Injectable({
    providedIn: 'root'
})
export class Search {
  
    constructor(private http:HttpClient,private tandu: Shared){}
 
    search(search:any){
        return this.http.post(SERVER_URL + '/search/smestaj',search,{responseType: 'text'});
    }

}