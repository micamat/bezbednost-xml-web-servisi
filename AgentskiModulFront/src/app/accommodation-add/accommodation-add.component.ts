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
  accommodation:any;

  constructor(private _accommodationService : AccommodationService,
    private formBuilder:FormBuilder,
    private router:Router,
    private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
      this.accommodationAdd = this.formBuilder.group({
        naziv:['',Validators.compose([Validators.required, Validators.pattern('[a-zA-Z 0-9!]+')])],
        idKategorijaSmestaja:['',Validators.compose([Validators.required, Validators.pattern('[a-z.A-Z 0-9!]+')])],
        opis:['',Validators.compose([Validators.required, Validators.pattern('[a-zA-Z 0-9!]+')])],
        idTipSmestaja:['',Validators.compose([Validators.required, Validators.pattern('[a-z.A-Z 0-9!]+')])],  
        drzava:['',Validators.compose([Validators.required, Validators.pattern('[a-zA-Z 0-9!]+')])],
        grad:['',Validators.compose([Validators.required, Validators.pattern('[a-zA-Z 0-9!]+')])],
        ulica:['',Validators.compose([Validators.required, Validators.pattern('[a-zA-Z 0-9!]+')])],
        broj:['',Validators.compose([Validators.required, Validators.pattern('[a-zA-Z 0-9!]+')])],  
      
      
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
    this.accommodation = this.temp;
    this.accommodation.idKategorijaSmestaja = this.temp.idKategorijaSmestaja.split(".",1)[0];
    this.accommodation.idTipSmestaja = this.temp.idTipSmestaja.split(".",1)[0];
    console.log(this.temp);

    this._accommodationService.addAccommmodation(this.accommodation).subscribe(
      data => {
              this.router.navigateByUrl("accommodation");
    });
  }

}
