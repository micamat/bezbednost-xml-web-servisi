import { Component, OnInit } from '@angular/core';
import { AccommodationService } from '../services/accommodation.service';
import { FormBuilder } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-ocupation',
  templateUrl: './ocupation.component.html',
  styleUrls: ['./ocupation.component.css']
})
export class OcupationComponent implements OnInit {

  ocupation : any;
  smestajId : any;
  rooms : any;

  constructor(private _accommodationService : AccommodationService,
    private formBuilder:FormBuilder,
    private router:Router,
    private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this._accommodationService.getAllOcupation().subscribe(
      data => {
        this.ocupation = data;
    });
    
  }

  odabrani(event : any){
    /*this.smestajId = event.target.value;
    
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
    }*/
      
  }

  delete(id:any){
    this._accommodationService.deleteOcupation(id).subscribe(
      data => {
        //ovo promenuti :D
          this._accommodationService.getAllOcupation().subscribe(
            data => {
              this.ocupation = data;
          });
    });
    
  }

}
