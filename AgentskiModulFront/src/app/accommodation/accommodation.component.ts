import { Component, OnInit } from '@angular/core';
import { AccommodationService } from '../services/accommodation.service';
import { FormBuilder } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-accommodation',
  templateUrl: './accommodation.component.html',
  styleUrls: ['./accommodation.component.css']
})
export class AccommodationComponent implements OnInit {

  accommodations : any;

  constructor(private _accommodationService : AccommodationService,
    private formBuilder:FormBuilder,
    private router:Router,
    private activatedRoute: ActivatedRoute) { }

  ngOnInit() {

    this._accommodationService.getAllAccommodation().subscribe(
      data => {
        this.accommodations = data;
    });
  }

  delete(id:any){
    this._accommodationService.deleteAccommodation(id).subscribe(
      data => {
        //ovo promenuti :D
        this._accommodationService.getAllAccommodation().subscribe(
          data => {
            this.accommodations = data;
        });
    });
    
  }

}
