import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AccommodationService } from '../services/accommodation.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-pricelist-change',
  templateUrl: './pricelist-change.component.html',
  styleUrls: ['./pricelist-change.component.css']
})
export class PricelistChangeComponent implements OnInit {

  pricelistAdd:FormGroup;
  submitted = false;
  accommodationsL : any;
  sobaTypeL : any;
  temp : any;
  priceId : any;
  price: any;

  constructor(private _accommodationService : AccommodationService,
    private formBuilder:FormBuilder,
    private router:Router,
    private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
      this._accommodationService.getAllAccommodation().subscribe(
        data => {
          this.accommodationsL = data;
      });
      this._accommodationService.getAllTypeRooms().subscribe(
        data => {
          this.sobaTypeL = data;
      });
      this.activatedRoute.paramMap.subscribe(
        params => {
            this.priceId = params.get('id');
            this._accommodationService.getPrice(this.priceId).subscribe(
                data => {
                  this.price = data;
                  this.pricelistAdd = this.formBuilder.group({
                    idSmestaj:['',Validators.compose([Validators.required, Validators.pattern('[a-z.A-Z 0-9!]+')])],
                    idTipSobe:['',Validators.compose([Validators.required, Validators.pattern('[a-z.A-Z 0-9!]+')])],  
                    datumDo:[this.price.datumDo,Validators.compose([Validators.required, Validators.pattern('[a-z.-A-Z 0-9!]+')])],
                    datumOd:[this.price.datumOd,Validators.compose([Validators.required, Validators.pattern('[a-z.-A-Z 0-9!]+')])],
                    cena:[this.price.cena,Validators.compose([Validators.required, Validators.pattern('[.0-9!]+')])]
                  });
                }
            );

        }
      );

  }

  get f() { return this.pricelistAdd.controls; }

  onSubmit(event:any) {
    this.submitted = true;
    this.temp = this.pricelistAdd.getRawValue();
    this.price = this.temp;
    this.price.id = this.priceId;
    this.price.idSmestaj = this.temp.idSmestaj.split(".",1)[0];
    this.price.idTipSobe = this.temp.idTipSobe.split(".",1)[0];
    
    this._accommodationService.addPrice(this.price).subscribe(
      data => {
              this.router.navigateByUrl("pricelist");
    });
  }

}
