import { Component, OnInit } from '@angular/core';
import { AccommodationService } from '../services/accommodation.service';
import { FormBuilder } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-room',
  templateUrl: './room.component.html',
  styleUrls: ['./room.component.css']
})
export class RoomComponent implements OnInit {

  accommodations : any;
  smestajId : any;
  rooms : any;

  constructor(private _accommodationService : AccommodationService,
    private formBuilder:FormBuilder,
    private router:Router,
    private activatedRoute: ActivatedRoute) { }
  ngOnInit() {
    this._accommodationService.getAllAccommodation().subscribe(
      data => {
        this.accommodations = data;
    });
    this._accommodationService.getAllRooms().subscribe(
      data => {
        this.rooms = data;
    });
    
  }

  odabrani(event : any){
    this.smestajId = event.target.value;
    
    if(this.smestajId=="All Accommodations"){
      this._accommodationService.getAllRooms().subscribe(
        data => {
          this.rooms = data;
      });
    }else{
      this._accommodationService.getByAccommodationRoom(this.smestajId).subscribe(
        data => {
          this.rooms = data;
      });
    }
      
  }

  delete(id:any){
    this._accommodationService.deleteRoom(id).subscribe(
      data => {
        //ovo promenuti :D
        if(this.smestajId=="All Accommodations"){
          this._accommodationService.getAllRooms().subscribe(
            data => {
              this.rooms = data;
          });
        }else{
          this._accommodationService.getByAccommodationRoom(this.smestajId).subscribe(
            data => {
              this.rooms = data;
          });
        }
    });
    
  }

}
