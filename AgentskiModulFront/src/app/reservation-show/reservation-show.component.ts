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

}
