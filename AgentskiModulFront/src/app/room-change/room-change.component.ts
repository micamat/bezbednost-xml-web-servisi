import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AccommodationService } from '../services/accommodation.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-room-change',
  templateUrl: './room-change.component.html',
  styleUrls: ['./room-change.component.css']
})
export class RoomChangeComponent implements OnInit {

  roomAdd:FormGroup;
  submitted = false;
  typeL : any;
  accommodationsL : any;
  temp : any;
  room: any;
  roomId : any;
  soba : any;

  constructor(private _accommodationService : AccommodationService,
    private formBuilder:FormBuilder,
    private router:Router,
    private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
      
      
      this._accommodationService.getAllTypeRooms().subscribe(
        data => {
          this.typeL = data;
      });
      this._accommodationService.getAllAccommodation().subscribe(
        data => {
          this.accommodationsL = data;
      });

      this.activatedRoute.paramMap.subscribe(
        params => {
            this.roomId = params.get('id');
            this._accommodationService.getRoom(this.roomId).subscribe(
                data => {
                  this.soba = data;
                  this.roomAdd = this.formBuilder.group({
                    naziv:[this.soba.naziv,Validators.compose([Validators.required, Validators.pattern('[a-zA-Z 0-9!]+')])],
                    opis:[this.soba.opis,Validators.compose([Validators.required, Validators.pattern('[a-zA-Z 0-9!]+')])],
                    idTipSobe:['',Validators.compose([Validators.required, Validators.pattern('[a-z.A-Z 0-9!]+')])],  
                    idSmestaj:['',Validators.compose([Validators.required, Validators.pattern('[a-z.A-Z 0-9!]+')])],
                    
                  });
                }
            );

        }
      );

  }

  get f() { return this.roomAdd.controls; }

  onSubmit(event:any) {
      this.submitted = true;
      this.temp = this.roomAdd.getRawValue();
      this.room = this.temp;
      this.room.slika = "";
      this.room.id = this.roomId;
      this.room.idTipSobe = this.temp.idTipSobe.split(".",1)[0];
      this.room.idSmestaj = this.temp.idSmestaj.split(".",1)[0];

      this._accommodationService.addRoom(this.room).subscribe(
        data => {
                this.router.navigateByUrl("room");
      });
  }


}
