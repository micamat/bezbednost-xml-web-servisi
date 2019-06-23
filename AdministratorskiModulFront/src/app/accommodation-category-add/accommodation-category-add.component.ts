import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AccommodationCategoryService } from '../services/accommodation-category.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-accommodation-category-add',
  templateUrl: './accommodation-category-add.component.html',
  styleUrls: ['./accommodation-category-add.component.css']
})
export class AccommodationCategoryAddComponent implements OnInit {

  accommodationCategoryAdd: FormGroup;
  accommodationCategory: any;

  constructor(
    private accommodationCategoryService: AccommodationCategoryService,
    private formBuilder:FormBuilder,
    private router:Router,    
    private activatedRoute: ActivatedRoute
  ) { }

  ngOnInit() {
    this.accommodationCategoryAdd = this.formBuilder.group({
      naziv:['',Validators.compose([Validators.required, Validators.pattern('[a-zA-Z 0-9!]+')])],
      opis:['',Validators.compose([Validators.required, Validators.pattern('[a-z.A-Z 0-9!]+')])]
    });
  }

  onSubmit(event: any){
    this.accommodationCategory = this.accommodationCategoryAdd.getRawValue();
    this.accommodationCategoryService.create(this.accommodationCategory).subscribe(
      data => {
        this.router.navigateByUrl("accommodation-category");
      }
    );
  }

}
