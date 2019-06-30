import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-hotels',
  templateUrl: './hotels.component.html',
  styleUrls: ['./hotels.component.css']
})
export class HotelsComponent implements OnInit {

  smestaji:any;
  accommodationId : any;

  constructor(
    private formBuilder:FormBuilder,
    private router:Router,
    private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(
      params => {
          this.accommodationId = params.get('id');
          
        });
    this.smestaji = JSON.parse(localStorage.getItem('search'));
    console.log("sdkljbgsdfjkb " + this.smestaji);
  }

}
