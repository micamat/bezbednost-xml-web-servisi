import { Component, OnInit } from '@angular/core';
import { AccommodationCategoryService } from '../services/accommodation-category.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-accommodation-category',
  templateUrl: './accommodation-category.component.html',
  styleUrls: ['./accommodation-category.component.css']
})
export class AccommodationCategoryComponent implements OnInit {

  accommodationCategories: any;

  constructor(
    private accommodationCategoryService: AccommodationCategoryService,
    private router: Router
  ) { }

  refresh(){
    this.accommodationCategoryService.getAll().subscribe(
      data => {
        this.accommodationCategories = data;
      }
    );
  }

  ngOnInit() {
    this.refresh();
  }

  delete(id: any){
    this.accommodationCategoryService.delete(id).subscribe(
      data => {
        this.refresh();
      }
    );
  }

}
