import { Component, OnInit } from '@angular/core';
import { AccommodationService } from '../services/accommodation.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { pipe } from "rxjs";
import { mapTo, delay } from 'rxjs/operators';

@Component({
  selector: 'app-accommodation-change',
  templateUrl: './accommodation-change.component.html',
  styleUrls: ['./accommodation-change.component.css']
})
export class AccommodationChangeComponent implements OnInit {

  accommodationAdd:FormGroup;
  submitted = false;
  user: any;
  public isLoged:boolean=false;
  us:any;
  categoryL:any;
  typeL : any;
  temp:any;
  accommodation:any;
  accommodationId : any;
  smestaj : any;
  typeF : any;
  kateF : any;

  constructor(private _accommodationService : AccommodationService,
    private formBuilder:FormBuilder,
    private router:Router,
    private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
      
      this._accommodationService.getAllCategoryAccommodation().subscribe(
        data => {
          this.categoryL = data;
      });
      this._accommodationService.getAllTypeAccommodation().subscribe(
        data => {
          this.typeL = data;
      });
      
      this.activatedRoute.paramMap.subscribe(
        params => {
            this.accommodationId = params.get('id');
            this._accommodationService.getAccommodation(this.accommodationId).subscribe(
                data => {
                  this.smestaj = data;
                  this.accommodationAdd = this.formBuilder.group({
                    naziv:[this.smestaj.naziv,Validators.compose([Validators.required, Validators.pattern('[a-zA-Z 0-9!]+')])],
                    idKategorijaSmestaja:[this.smestaj.nazivKategorijaSmestaja,Validators.compose([Validators.required, Validators.pattern('[0-9!]+')])],
                    opis:[this.smestaj.opis,Validators.compose([Validators.required, Validators.pattern('[a-zA-Z 0-9!]+')])],
                    idTipSmestaja:["",Validators.compose([Validators.required, Validators.pattern('[0-9!]+')])],  
                    drzava:[this.smestaj.drzava,Validators.compose([Validators.required, Validators.pattern('[a-zA-Z 0-9!]+')])],
                    grad:[this.smestaj.grad,Validators.compose([Validators.required, Validators.pattern('[a-zA-Z 0-9!]+')])],
                    ulica:[this.smestaj.ulica,Validators.compose([Validators.required, Validators.pattern('[a-zA-Z 0-9!]+')])],
                    broj:[this.smestaj.broj,Validators.compose([Validators.required, Validators.pattern('[a-zA-Z 0-9!]+')])], 
                    idAgent:["",Validators.compose([Validators.required, Validators.pattern('[0-9!]+')])],  
                    slika:["",Validators.compose([Validators.required, Validators.pattern('[a-z.A-Z 0-9!]+')])],  
                    kapacitet:[this.smestaj.kapacitet,Validators.compose([Validators.required, Validators.pattern('[0-9!]+')])],  
                  });
                  this.categoryL.forEach(element => {
                    if(element.nazivKategorijaSmestaja == this.smestaj.nazivKategorijaSmestaja){
                        this.kateF = this.smestaj.nazivKategorijaSmestaja
                    }
                });
                this.typeL.forEach(element => {
                  if(element.nazivTipSmestaja == this.smestaj.nazivTipSmestaja){
                      this.typeF = this.smestaj.nazivTipSmestaja
                  }
              });
                }
            );
        }
      );
      

  }

  get f() { return this.accommodationAdd.controls; }


  onSubmit(event:any) {
    this.submitted = true;
    this.temp = this.accommodationAdd.getRawValue();
    this.accommodation = this.temp;
    this.accommodation.idKategorijaSmestaja = this.temp.idKategorijaSmestaja.split(".",1)[0];
    this.accommodation.idTipSmestaja = this.temp.idTipSmestaja.split(".",1)[0];
    this.accommodation.idLokacija = this.smestaj.idLokacija;
    this.accommodation.id = this.smestaj.id;
    console.log(this.temp);

    this._accommodationService.addAccommmodation(this.accommodation).subscribe(
      data => {
              this.router.navigateByUrl("accommodation");
    });
  }

}
