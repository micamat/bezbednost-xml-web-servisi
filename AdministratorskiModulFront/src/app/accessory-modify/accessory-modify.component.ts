import { Component, OnInit } from '@angular/core';
import { Validators, FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AccessoryService } from '../services/accessory.service';

@Component({
  selector: 'app-accessory-modify',
  templateUrl: './accessory-modify.component.html',
  styleUrls: ['./accessory-modify.component.css']
})
export class AccessoryModifyComponent implements OnInit {


  accessoryAdd: FormGroup;
  accessory: any;
  accessoryId: any;
  current: any;

  constructor(private accessoryService: AccessoryService,
    private formBuilder:FormBuilder,
    private router:Router,
    private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(
      params => {
          this.accessoryId = params.get('id');

          this.accessoryService.getById(this.accessoryId).subscribe(
              data => {
                this.accessory = data;
                this.accessoryAdd = this.formBuilder.group({
                  naziv:[this.accessory.naziv,Validators.compose([Validators.required, Validators.pattern('[a-zA-Z 0-9!]+')])],
                  opis:[this.accessory.opis,Validators.compose([Validators.required, Validators.pattern('[a-z.A-Z 0-9!]+')])]
                });
              }
          );
      }
    );
  }

  onSubmit(event:any) {
    this.current = this.accessoryAdd.getRawValue();
    this.accessoryService.update(this.current, this.accessoryId).subscribe(
      data => {
        this.router.navigateByUrl("accessory");
    });
  }

}
