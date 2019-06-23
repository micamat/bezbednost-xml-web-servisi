import { Injectable } from '@angular/core';
import { SERVER_URL } from '../app.constants';
import { HttpClient } from '@angular/common/http';
import { text } from '@angular/core/src/render3';

@Injectable({
  providedIn: 'root'
})
export class AgentService {

  constructor(private http:HttpClient) { }

  public getAll(){
    return this.http.get(SERVER_URL + "/agent"); 
  }

  public getById(id: any){
    return this.http.get(SERVER_URL + '/agent/' + id);
  }

  public create(agent: any){
    return this.http.post(SERVER_URL + "/agent", agent, {responseType: 'text'});
  }

  public update(agent: any, id: any){
    return this.http.put(SERVER_URL + "/agent/" + id, agent, {params: agent});
  }

  public delete(id: any){
    return this.http.delete(SERVER_URL + "/agent/" + id);
  }
}
