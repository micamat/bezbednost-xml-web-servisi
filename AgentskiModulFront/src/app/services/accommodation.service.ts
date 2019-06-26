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

  getAllRooms(){
    return this.http.get(SERVER_URL + "/soba");
  }

  getByAccommodationRoom(id : any){
    return this.http.get(SERVER_URL + "/soba/smestaj/" + id);
  }

  addRoom(room : any){
      return this.http.post(SERVER_URL + '/soba',room,{responseType: 'text'});
  }

  getAllTypeRooms(){
    return this.http.get(SERVER_URL + '/tip-sobe');
  }

  deleteRoom(id:any){
    return this.http.delete(SERVER_URL + "/soba/" + id,{responseType: 'text'});
  }

  getRoom(id:any){
    return this.http.get(SERVER_URL + "/soba/" + id);
  }

  getAllOcupation(){
    return this.http.get(SERVER_URL + "/zauzece");
  }
  
  getAllStatusRoom(){
    return this.http.get(SERVER_URL + "/status-sobe");
  }

  addOcupation(ocupation : any){
    return this.http.post(SERVER_URL + '/zauzece',ocupation,{responseType: 'text'});
  }

  getOcupation(id:any){
    return this.http.get(SERVER_URL + "/zauzece/" + id);
  }

  deleteOcupation(id:any){
    return this.http.delete(SERVER_URL + "/zauzece/" + id,{responseType: 'text'});
  }

  getAllPricelist(){
    return this.http.get(SERVER_URL + "/cenovnik");
  }

  addPrice(price : any){
    return this.http.post(SERVER_URL + '/cenovnik',price,{responseType: 'text'});
  }

  deletePrice(id:any){
    return this.http.delete(SERVER_URL + "/cenovnik/" + id,{responseType: 'text'});
  }

  getPrice(id:any){
    return this.http.get(SERVER_URL + "/cenovnik/" + id);
  }
  
  getAlUusluga(){
    return this.http.get(SERVER_URL + "/usluga");
  }

  getAllComments(){
    return this.http.get(SERVER_URL + "/komentar");
  }
  
  addReservation(reservation : any){
    return this.http.post(SERVER_URL + '/rezervacija',reservation,{responseType: 'text'});
  }

  getAllReservation(){
    return this.http.get(SERVER_URL + "/rezervacija");
  }
  
  getAllMessages(id:any){
    return this.http.get(SERVER_URL + "/poruka/rezervacija/" + id);
  }

  putPotvrdjeno(izmena : any){
    return this.http.put(SERVER_URL + '/rezervacija',izmena,{responseType: 'text'});
  }

  getAllReservationRooms(id:any){
    return this.http.get(SERVER_URL + "/rezervacija/" + id);
  }
  
}
