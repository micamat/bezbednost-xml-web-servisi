import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AccommodationService } from '../services/accommodation.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-room-add',
  templateUrl: './room-add.component.html',
  styleUrls: ['./room-add.component.css']
})
export class RoomAddComponent implements OnInit {

  roomAdd:FormGroup;
  submitted = false;
  typeL : any;
  accommodationsL : any;
  temp : any;
  room: any;

  constructor(private _accommodationService : AccommodationService,
    private formBuilder:FormBuilder,
    private router:Router,
    private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
      this.roomAdd = this.formBuilder.group({
        naziv:['',Validators.compose([Validators.required, Validators.pattern('[a-zA-Z 0-9!]+')])],
        opis:['',Validators.compose([Validators.required, Validators.pattern('[a-zA-Z 0-9!]+')])],
        idTipSobe:['',Validators.compose([Validators.required, Validators.pattern('[a-z.A-Z 0-9!]+')])],  
        idSmestaj:['',Validators.compose([Validators.required, Validators.pattern('[a-z.A-Z 0-9!]+')])],
        
      });
      
      this._accommodationService.getAllTypeRooms().subscribe(
        data => {
          this.typeL = data;
      });
      this._accommodationService.getAllAccommodation().subscribe(
        data => {
          this.accommodationsL = data;
      });

  }

  get f() { return this.roomAdd.controls; }

  onSubmit(event:any) {
    this.submitted = true;
    this.temp = this.roomAdd.getRawValue();
    this.room = this.temp;
    this.room.slika = "";
    this.room.idTipSobe = this.temp.idTipSobe.split(".",1)[0];
    this.room.idSmestaj = this.temp.idSmestaj.split(".",1)[0];

    this._accommodationService.addRoom(this.room).subscribe(
      data => {
              this.router.navigateByUrl("room");
    });
  }

}
