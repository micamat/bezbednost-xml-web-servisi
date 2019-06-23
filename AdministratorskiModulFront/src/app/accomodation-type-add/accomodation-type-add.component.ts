import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AccommodationTypeService } from '../services/accommodation-type.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-accomodation-type-add',
  templateUrl: './accomodation-type-add.component.html',
  styleUrls: ['./accomodation-type-add.component.css']
})
export class AccomodationTypeAddComponent implements OnInit {

  accommodationTypeAdd: FormGroup;
  accommodationType: any;

  constructor(
    private accommodationTypeService: AccommodationTypeService,
    private formBuilder:FormBuilder,
    private router:Router,
    private activatedRoute: ActivatedRoute
  ) { }

  ngOnInit() {
    this.accommodationTypeAdd = this.formBuilder.group({
      naziv:['',Validators.compose([Validators.required, Validators.pattern('[a-zA-Z 0-9!]+')])],
      opis:['',Validators.compose([Validators.required, Validators.pattern('[a-z.A-Z 0-9!]+')])]
    });
  }

  onSubmit(event: any){
    this.accommodationType = this.accommodationTypeAdd.getRawValue();
    this.accommodationTypeService.create(this.accommodationType).subscribe(
      data => {
        this.router.navigateByUrl("accommodation-type");
      }
    );
  }
}
