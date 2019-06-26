import { Component, OnInit } from '@angular/core';
import { AccommodationService } from '../services/accommodation.service';
import { FormBuilder } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-reservation-rooms',
  templateUrl: './reservation-rooms.component.html',
  styleUrls: ['./reservation-rooms.component.css']
})
export class ReservationRoomsComponent implements OnInit {

  reservation : any;
  accommodationId : any;
  
  constructor(private _accommodationService : AccommodationService,
    private formBuilder:FormBuilder,
    private router:Router,
    private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(
      params => {
          this.accommodationId = params.get('id');
          this._accommodationService.getAllReservationRooms(this.accommodationId).subscribe(
            data => {
              this.reservation = data;
          })
        });
  }

}
