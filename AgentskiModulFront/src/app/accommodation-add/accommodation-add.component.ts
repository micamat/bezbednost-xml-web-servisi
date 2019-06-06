import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { AccommodationService } from '../services/accommodation.service';

@Component({
  selector: 'app-accommodation-add',
  templateUrl: './accommodation-add.component.html',
  styleUrls: ['./accommodation-add.component.css']
})
export class AccommodationAddComponent implements OnInit {
  
  accommodationAdd:FormGroup;
  submitted = false;
  user: any;
  public isLoged:boolean=false;
  us:any;
  categoryL:any;
  typeL : any;
  temp:any;

  constructor(private _accommodationService : AccommodationService,
    private formBuilder:FormBuilder,
    private router:Router,
    private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
      this.accommodationAdd = this.formBuilder.group({
        name:['',Validators.compose([Validators.required, Validators.pattern('[a-zA-Z 0-9!]+')])],
        category:['',Validators.compose([Validators.required, Validators.pattern('[a-zA-Z 0-9!]+')])],
        description:['',Validators.compose([Validators.required, Validators.pattern('[a-zA-Z 0-9!]+')])],
        type:['',Validators.compose([Validators.required, Validators.pattern('[a-zA-Z 0-9!]+')])],  
        country:['',Validators.compose([Validators.required, Validators.pattern('[a-zA-Z 0-9!]+')])],
        town:['',Validators.compose([Validators.required, Validators.pattern('[a-zA-Z 0-9!]+')])],
        street:['',Validators.compose([Validators.required, Validators.pattern('[a-zA-Z 0-9!]+')])],
        number:['',Validators.compose([Validators.required, Validators.pattern('[a-zA-Z 0-9!]+')])],  
      
      
      });
      this._accommodationService.getAllCategoryAccommodation().subscribe(
        data => {
          this.categoryL = data;
      });
      this._accommodationService.getAllTypeAccommodation().subscribe(
        data => {
          this.typeL = data;
      });
      
      

  }

  get f() { return this.accommodationAdd.controls; }

  onSubmit(event:any) {
    this.submitted = true;
    this.temp = this.accommodationAdd.getRawValue();
    console.log(this.temp);
  }

}
