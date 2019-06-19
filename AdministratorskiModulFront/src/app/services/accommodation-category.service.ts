import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SERVER_URL } from '../app.constants';

@Injectable({
  providedIn: 'root'
})
export class AccommodationCategoryService {

  constructor(private http:HttpClient) { }

  public getAll(){
    return this.http.get(SERVER_URL + "/kategorijaSmestaja"); 
  }

  public getById(id: any){
    return this.http.get(SERVER_URL + '/kategorijaSmestaja/' + id);
  }

  public create(agent: any){
    return this.http.post(SERVER_URL + "/kategorijaSmestaja", agent, {responseType: 'text'});
  }

  public update(agent: any, id: any){
    return this.http.put(SERVER_URL + "/kategorijaSmestaja/" + id, agent, {params: agent});
  }

  public delete(id: any){
    return this.http.delete(SERVER_URL + "/kategorijaSmestaja/" + id);
  }
}
