import { Injectable } from '@angular/core';
import { SERVER_URL } from '../app.constant';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AccommodationService {

  constructor(private http:HttpClient){

  }

  getAllCategoryAccommodation(){
    return this.http.get(SERVER_URL + '/kategorija-smestaja');
  }

  getAllTypeAccommodation(){
    return this.http.get(SERVER_URL + '/tip-smestaja');
  }

}
