import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SERVER_URL } from '../app.constants';

@Injectable({
  providedIn: 'root'
})
export class AccommodationTypeService {

  constructor(private http:HttpClient) { }

  public getAll(){
    return this.http.get(SERVER_URL + "/tipSmestaja"); 
  }

  public getById(id: any){
    return this.http.get(SERVER_URL + '/tipSmestaja/' + id);
  }

  public create(agent: any){
    return this.http.post(SERVER_URL + "/tipSmestaja", agent, {responseType: 'text'});
  }

  public update(agent: any, id: any){
    return this.http.put(SERVER_URL + "/tipSmestaja/" + id, agent, {params: agent});
  }

  public delete(id: any){
    return this.http.delete(SERVER_URL + "/tipSmestaja/" + id);
  }
}
