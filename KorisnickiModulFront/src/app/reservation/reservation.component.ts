import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.css']
})
export class ReservationComponent implements OnInit {

  smestaji : any;
  accommodationId : any;
  accommodation : any;

  constructor(private formBuilder:FormBuilder,
    private router:Router,
    private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(
      params => {
          this.accommodationId = params.get('id');

          this.smestaji = JSON.parse(localStorage.getItem('search'));
          this.smestaji.forEach(element => {
              if(element.id == this.accommodationId){
                  this.accommodation = element;
                  console.log(this.accommodation);
              }
          });
          
        });
    
    console.log("sdkljbgsdfjkb " + this.smestaji);
  }

}
