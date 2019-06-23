import { Component, OnInit } from '@angular/core';
import { Validators, FormGroup, FormBuilder } from '@angular/forms';
import { AccommodationCategoryService } from '../services/accommodation-category.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-accommodation-category-modify',
  templateUrl: './accommodation-category-modify.component.html',
  styleUrls: ['./accommodation-category-modify.component.css']
})
export class AccommodationCategoryModifyComponent implements OnInit {

  accommodationCategoryAdd: FormGroup;
  accommodationCategory: any;
  accommodationCategoryId: any;
  current: any;

  constructor(
    private accommodationCategoryService: AccommodationCategoryService,
    private formBuilder:FormBuilder,
    private router:Router,
    private activatedRoute: ActivatedRoute
  ) { }

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(
      params => {
          this.accommodationCategoryId = params.get('id');

          this.accommodationCategoryService.getById(this.accommodationCategoryId).subscribe(
              data => {
                this.accommodationCategory = data;
                this.accommodationCategoryAdd = this.formBuilder.group({
                  naziv:[this.accommodationCategory.naziv,Validators.compose([Validators.required, Validators.pattern('[a-zA-Z 0-9!]+')])],
                  opis:[this.accommodationCategory.opis,Validators.compose([Validators.required, Validators.pattern('[a-z.A-Z 0-9!]+')])]
                });
              }
          );
      }
    );
  }

  onSubmit(event:any) {
    this.current = this.accommodationCategoryAdd.getRawValue();
    this.accommodationCategoryService.update(this.current, this.accommodationCategoryId).subscribe(
      data => {
        this.router.navigateByUrl("accommodation-category");
    });
  }

}
