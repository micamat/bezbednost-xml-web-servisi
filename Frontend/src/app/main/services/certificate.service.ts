import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SERVER_URL } from '../main.constant';
import { text } from '@angular/core/src/render3';

@Injectable({
  providedIn: 'root'
})
export class CertificateService {

  constructor(private http:HttpClient) { }

  createCertificate(certificate){
    return this.http.post(SERVER_URL+'/certificate/create',certificate,{responseType: 'text'});
  }

  createKeystore(keystore){
    return this.http.post(SERVER_URL+'/certificate/createKeystore',keystore,{responseType: 'text'});
  }
  
}
