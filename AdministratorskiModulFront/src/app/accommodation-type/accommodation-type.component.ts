import { Component, OnInit } from '@angular/core';
import { AccommodationTypeService } from '../services/accommodation-type.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-accommodation-type',
  templateUrl: './accommodation-type.component.html',
  styleUrls: ['./accommodation-type.component.css']
})
export class AccommodationTypeComponent implements OnInit {

  accommodationTypes: any;

  constructor(
    private accommodationTypeService: AccommodationTypeService,
    private router: Router
  ) { }

  refresh(){
    this.accommodationTypeService.getAll().subscribe(
      data => {
        this.accommodationTypes = data;
      }
    );
  }

  ngOnInit() {
    this.refresh();
  }

  delete(id: any){
    this.accommodationTypeService.delete(id).subscribe(
      data => {
        this.refresh();
      }
    );
  }

}
