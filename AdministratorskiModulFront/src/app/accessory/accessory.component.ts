import { Component, OnInit } from '@angular/core';
import { AccommodationTypeService } from '../services/accommodation-type.service';
import { Router } from '@angular/router';
import { AccessoryService } from '../services/accessory.service';

@Component({
  selector: 'app-accessory',
  templateUrl: './accessory.component.html',
  styleUrls: ['./accessory.component.css']
})
export class AccessoryComponent implements OnInit {

  accessories: any;

  constructor(
    private accessoryService: AccessoryService,
    private router: Router
  ) { }

  refresh(){
    this.accessoryService.getAll().subscribe(
      data => {
        this.accessories = data;
      }
    );
  }

  ngOnInit() {
    this.refresh();
  }

  delete(id: any){
    this.accessoryService.delete(id).subscribe(
      data => {
        this.refresh();
      }
    );
  }

}
