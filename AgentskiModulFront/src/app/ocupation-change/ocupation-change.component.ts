import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AccommodationService } from '../services/accommodation.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-ocupation-change',
  templateUrl: './ocupation-change.component.html',
  styleUrls: ['./ocupation-change.component.css']
})
export class OcupationChangeComponent implements OnInit {

  ocupationChange:FormGroup;
  submitted = false;
  accommodationsL : any;
  sobaL : any;
  sobaStatusL : any;
  temp : any;
  ocupation: any;
  ocupationId : any;

  constructor(private _accommodationService : AccommodationService,
    private formBuilder:FormBuilder,
    private router:Router,
    private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
      
      this._accommodationService.getAllAccommodation().subscribe(
        data => {
          this.accommodationsL = data;
      });
      this._accommodationService.getAllRooms().subscribe(
        data => {
          this.sobaL = data;
      });
      this._accommodationService.getAllStatusRoom().subscribe(
        data => {
          this.sobaStatusL = data;
      });

      this.activatedRoute.paramMap.subscribe(
        params => {
            this.ocupationId = params.get('id');
            this._accommodationService.getOcupation(this.ocupationId).subscribe(
                data => {
                  this.ocupation = data;
                  this.ocupationChange = this.formBuilder.group({
                    idSoba:['',Validators.compose([Validators.required, Validators.pattern('[a-z.A-Z 0-9!]+')])],
                    idStatusSobe:['',Validators.compose([Validators.required, Validators.pattern('[a-z.A-Z 0-9!]+')])],  
                    datumDo:[this.ocupation.datumDo,Validators.compose([Validators.required, Validators.pattern('[a-z.-A-Z 0-9!]+')])],
                    datumOd:[this.ocupation.datumOd,Validators.compose([Validators.required, Validators.pattern('[a-z.-A-Z 0-9!]+')])]
                  });
                }
            );

        }
      );

  }

  get f() { return this.ocupationChange.controls; }

  onSubmit(event:any) {
    this.submitted = true;
    this.temp = this.ocupationChange.getRawValue();
    this.ocupation = this.temp;
    this.ocupation.id = this.ocupationId;
    this.ocupation.idSoba = this.temp.idSoba.split(".",1)[0];
    this.ocupation.idStatusSobe = this.temp.idStatusSobe.split(".",1)[0];
    
    this._accommodationService.addOcupation(this.ocupation).subscribe(
      data => {
              this.router.navigateByUrl("ocupation");
    });


  }



}
