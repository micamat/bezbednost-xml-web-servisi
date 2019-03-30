import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SERVER_URL } from '../main.constant';
import { text } from '@angular/core/src/render3';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CertificateService {

  constructor(private http:HttpClient) { }

  createCertificate(certificate, trustStore){
    return this.http.post(SERVER_URL+'/certificate/create/'+trustStore, certificate, {responseType: 'text'});
  }

  createKeystore(keystore){
    return this.http.post(SERVER_URL+'/certificate/createKeystore',keystore,{responseType: 'text'});
  }
  
  getAllAdminKeystores(){
    return this.http.get(SERVER_URL+'/certificate/getAllAdminKeystores');
  }

  getAllTransferStore(){
    return this.http.get(SERVER_URL+'/certificate/getAllTransferKeystores');
  }

  getAllNodes(){
    return this.http.get(SERVER_URL+'/nodes/all');
  }

  transfer(alias:any, keystore:any){
    return this.http.post(SERVER_URL+'/certificate/transfer/'+alias+'/'+keystore, {params: alias, keystore});
  }

  getAllChildernNodes(node:any){
    return this.http.post(SERVER_URL+'/nodes/children',node);
  }

  getAllCertificate(){
    return this.http.get(SERVER_URL+'/certificate/getAllCertificate');
  }

  getCertificateDetails(){
    return this.http.get(SERVER_URL+'/certificate/certificates');
  }

  revoke(id:string):Observable<any>{
    return this.http.put(SERVER_URL+'/certificate/'+id, {params: id});
  }
}
