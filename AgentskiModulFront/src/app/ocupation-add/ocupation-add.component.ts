import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AccommodationService } from '../services/accommodation.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-ocupation-add',
  templateUrl: './ocupation-add.component.html',
  styleUrls: ['./ocupation-add.component.css']
})
export class OcupationAddComponent implements OnInit {

  ocupationAdd:FormGroup;
  submitted = false;
  accommodationsL : any;
  sobaL : any;
  sobaStatusL : any;
  temp : any;
  ocupation: any;

  constructor(private _accommodationService : AccommodationService,
    private formBuilder:FormBuilder,
    private router:Router,
    private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
      this.ocupationAdd = this.formBuilder.group({
        idSoba:['',Validators.compose([Validators.required, Validators.pattern('[a-z.A-Z 0-9!]+')])],
        idStatusSobe:['',Validators.compose([Validators.required, Validators.pattern('[a-z.A-Z 0-9!]+')])],  
        datumDo:['',Validators.compose([Validators.required, Validators.pattern('[a-z.-A-Z 0-9!]+')])],
        datumOd:['',Validators.compose([Validators.required, Validators.pattern('[a-z.-A-Z 0-9!]+')])]
      });
      
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

  }

  get f() { return this.ocupationAdd.controls; }

  onSubmit(event:any) {
    this.submitted = true;
    this.temp = this.ocupationAdd.getRawValue();
    this.ocupation = this.temp;
    this.ocupation.idSoba = this.temp.idSoba.split(".",1)[0];
    this.ocupation.idStatusSobe = this.temp.idStatusSobe.split(".",1)[0];
    
    this._accommodationService.addOcupation(this.ocupation).subscribe(
      data => {
              this.router.navigateByUrl("ocupation");
    });
  }


}
