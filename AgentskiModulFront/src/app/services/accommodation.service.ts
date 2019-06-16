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

  addAccommmodation(accommodation : any){
    return this.http.post(SERVER_URL + '/smestaj',accommodation,{responseType: 'text'});
  }

  getAllAccommodation(){
    return this.http.get(SERVER_URL + "/smestaj");
  }

  deleteAccommodation(id:any){
    return this.http.delete(SERVER_URL + "/smestaj/" + id,{responseType: 'text'});
  }

  getAccommodation(id:any){
    return this.http.get(SERVER_URL + "/smestaj/" + id);
  }

}
