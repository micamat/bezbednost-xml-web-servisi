import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SERVER_URL } from '../main.constant';
import { text } from '@angular/core/src/render3';

@Injectable({
  providedIn: 'root'
})
export class NodeService {

  constructor(private http:HttpClient) { }

  createNode(node){
    console.log("node: " + node.comonName);
    return this.http.post(SERVER_URL+'/nodes',node,{responseType: 'text'});
  }

  getNodes(){
    return this.http.get(SERVER_URL+'/nodes/all');
  }
  
}
