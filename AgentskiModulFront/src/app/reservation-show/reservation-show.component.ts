import { Component, OnInit } from '@angular/core';
import { AccommodationService } from '../services/accommodation.service';
import { FormBuilder } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-reservation-show',
  templateUrl: './reservation-show.component.html',
  styleUrls: ['./reservation-show.component.css']
})
export class ReservationShowComponent implements OnInit {

  reservation : any;
  potvrdjeno = { 
    idRezervacija: 6, 
    statusRezervacije: "Potvrdjeno" 
 }
 otkazano = { 
  idRezervacija: 6, 
  statusRezervacije: "Otkazano"
}
  
  constructor(private _accommodationService : AccommodationService,
    private formBuilder:FormBuilder,
    private router:Router,
    private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this._accommodationService.getAllReservation().subscribe(
      data => {
        this.reservation = data;
    });
  }

  potvrda(id:any){
    this.potvrdjeno.idRezervacija = id;
    this._accommodationService.putPotvrdjeno(this.potvrdjeno).subscribe(
      data => {
        this._accommodationService.getAllReservation().subscribe(
          data => {
            this.reservation = data;
        });
    });
  }

  otkaz(id:any){
    this.otkazano.idRezervacija = id;
    this._accommodationService.putPotvrdjeno(this.otkazano).subscribe(
      data => {
        this._accommodationService.getAllReservation().subscribe(
          data => {
            this.reservation = data;
        });
    });
  }
  

}
