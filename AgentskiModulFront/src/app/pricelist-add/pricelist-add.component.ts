import { Component, OnInit } from '@angular/core';
import { AccommodationService } from '../services/accommodation.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-pricelist-add',
  templateUrl: './pricelist-add.component.html',
  styleUrls: ['./pricelist-add.component.css']
})
export class PricelistAddComponent implements OnInit {

  pricelistAdd:FormGroup;
  submitted = false;
  accommodationsL : any;
  sobaTypeL : any;
  temp : any;
  price: any;

  constructor(private _accommodationService : AccommodationService,
    private formBuilder:FormBuilder,
    private router:Router,
    private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
      this.pricelistAdd = this.formBuilder.group({
        idSmestaj:['',Validators.compose([Validators.required, Validators.pattern('[a-z.A-Z 0-9!]+')])],
        idTipSobe:['',Validators.compose([Validators.required, Validators.pattern('[a-z.A-Z 0-9!]+')])],  
        datumDo:['',Validators.compose([Validators.required, Validators.pattern('[a-z.-A-Z 0-9!]+')])],
        datumOd:['',Validators.compose([Validators.required, Validators.pattern('[a-z.-A-Z 0-9!]+')])],
        cena:['',Validators.compose([Validators.required, Validators.pattern('[.0-9!]+')])],
        brojDanaZaOtkazivanje:['',Validators.compose([Validators.required, Validators.pattern('[a-z.-A-Z 0-9!]+')])]
      });
      
      this._accommodationService.getAllAccommodation().subscribe(
        data => {
          this.accommodationsL = data;
      });
      this._accommodationService.getAllTypeRooms().subscribe(
        data => {
          this.sobaTypeL = data;
      });

  }

  get f() { return this.pricelistAdd.controls; }

  onSubmit(event:any) {
    this.submitted = true;
    this.temp = this.pricelistAdd.getRawValue();
    this.price = this.temp;
    
    this._accommodationService.addPrice(this.price).subscribe(
      data => {
              this.router.navigateByUrl("pricelist");
    });
  }

}
