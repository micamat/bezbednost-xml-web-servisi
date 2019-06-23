import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { NodeService } from '../services/node.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-create-node',
  templateUrl: './create-node.component.html',
  styleUrls: ['./create-node.component.css']
})
export class CreateNodeComponent implements OnInit {
  nodeForm:FormGroup;
  submitted = false;
  node: any;
  parents: any;

  constructor(
    private _nodeService : NodeService,
    private formBuilder:FormBuilder,
    private router:Router,
    private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
      this.nodeForm = this.formBuilder.group({
        comonName:['',Validators.required],
        surname:['',Validators.required],
        givenName:['',Validators.required],
        organizationName:['',Validators.required],
        organizationUnitName:['',Validators.required],
        countryName:['',Validators.required],
        email:['',Validators.required],
        parentId:['',Validators.required],
      });

      this._nodeService.getNodes().subscribe(
        data => this.parents = data
      );
  }
  
  get f() { return this.nodeForm.controls; }

  onSubmit(event:any) {
    this.submitted = true;
    this.node = this.nodeForm.getRawValue();
    console.log(this.node);
    this._nodeService.createNode(this.node).subscribe(
      data => {
        console.log("Cvor kreiran")
        this.router.navigateByUrl("adminPage");
      });
  }

}

