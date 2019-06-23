import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SERVER_URL } from '../app.constants';

@Injectable({
  providedIn: 'root'
})
export class AccessoryService {

  constructor(private http:HttpClient) { }

  public getAll(){
    return this.http.get(SERVER_URL + "/tipUsluge"); 
  }

  public getById(id: any){
    return this.http.get(SERVER_URL + '/tipUsluge/' + id);
  }

  public create(agent: any){
    return this.http.post(SERVER_URL + "/tipUsluge", agent, {responseType: 'text'});
  }

  public update(agent: any, id: any){
    return this.http.put(SERVER_URL + "/tipUsluge/" + id, agent, {params: agent});
  }

  public delete(id: any){
    return this.http.delete(SERVER_URL + "/tipUsluge/" + id);
  }
}
