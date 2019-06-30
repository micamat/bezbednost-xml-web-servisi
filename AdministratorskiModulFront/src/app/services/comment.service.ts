import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SERVER_URL } from '../app.constants';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  constructor(private http:HttpClient) { }

  public getAll(){
    return this.http.get(SERVER_URL + "/komentar"); 
  }

  public hideComment(id: any){
    return this.http.get(SERVER_URL + "/komentar/hide/" + id);
  }
}
