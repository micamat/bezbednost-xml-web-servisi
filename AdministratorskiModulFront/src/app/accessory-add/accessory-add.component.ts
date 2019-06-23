import { Component, OnInit } from '@angular/core';
import { Validators, FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AccessoryService } from '../services/accessory.service';

@Component({
  selector: 'app-accessory-add',
  templateUrl: './accessory-add.component.html',
  styleUrls: ['./accessory-add.component.css']
})
export class AccessoryAddComponent implements OnInit {

  accessoryAdd: FormGroup;
  accessory: any;

  constructor(
    private accessoryService: AccessoryService,
    private formBuilder:FormBuilder,
    private router:Router,
    private activatedRoute: ActivatedRoute
  ) { }

  ngOnInit() {
    this.accessoryAdd = this.formBuilder.group({
      naziv:['',Validators.compose([Validators.required, Validators.pattern('[a-zA-Z 0-9!]+')])],
      opis:['',Validators.compose([Validators.required, Validators.pattern('[a-z.A-Z 0-9!]+')])]
    });
  }

  onSubmit(event: any){
    this.accessory = this.accessoryAdd.getRawValue();
    this.accessoryService.create(this.accessory).subscribe(
      data => {
        this.router.navigateByUrl("accessory");
      }
    );
  }

}
