import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AccommodationTypeService } from '../services/accommodation-type.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-accommodation-type-modify',
  templateUrl: './accommodation-type-modify.component.html',
  styleUrls: ['./accommodation-type-modify.component.css']
})
export class AccommodationTypeModifyComponent implements OnInit {

  accommodationTypeAdd: FormGroup;
  accommodationType: any;
  accommodationTypeId: any;
  current: any;

  constructor(
    private accommodationTypeService: AccommodationTypeService,
    private formBuilder:FormBuilder,
    private router:Router,
    private activatedRoute: ActivatedRoute
  ) { }

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(
      params => {
          this.accommodationTypeId = params.get('id');

          this.accommodationTypeService.getById(this.accommodationTypeId).subscribe(
              data => {
                this.accommodationType = data;
                this.accommodationTypeAdd = this.formBuilder.group({
                  naziv:[this.accommodationType.naziv,Validators.compose([Validators.required, Validators.pattern('[a-zA-Z 0-9!]+')])],
                  opis:[this.accommodationType.opis,Validators.compose([Validators.required, Validators.pattern('[a-z.A-Z 0-9!]+')])]
                });
              }
          );
      }
    );
  }

  onSubmit(event:any) {
    this.current = this.accommodationTypeAdd.getRawValue();
    this.accommodationTypeService.update(this.current, this.accommodationTypeId).subscribe(
      data => {
        this.router.navigateByUrl("accommodation-type");
    });
  }

}
