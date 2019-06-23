import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SERVER_URL } from '../app.constant';

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  constructor(private http:HttpClient) { }

  getAllAvailableAccommodations(accommodations){
    return this.http.get(SERVER_URL + '/smestaj',accommodations);
  }

  getAllAccommodations(){
    return this.http.get(SERVER_URL + '/smestaj/getAll')
  }
}
