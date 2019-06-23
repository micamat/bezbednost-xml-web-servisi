import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  SingIn:FormGroup;
  submitted = false;
  user: any;
  public isLoged:boolean=false;
  us:any;

  constructor(
    private formBuilder:FormBuilder,
    private router:Router,
    private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
      this.SingIn = this.formBuilder.group({
        name:['',Validators.compose([Validators.required, Validators.pattern('[a-zA-Z 0-9!]+')])],
        surname:['',Validators.compose([Validators.required, Validators.pattern('[a-zA-Z 0-9!]+')])],
        address:['',Validators.compose([Validators.required, Validators.pattern('[a-zA-Z 0-9!]+')])],
        email:['',Validators.compose([Validators.required, Validators.email])],
        username:['',Validators.compose([Validators.required, Validators.pattern('[a-zA-Z 0-9!]+')])],
        password:['',Validators.compose([Validators.required, Validators.pattern('[a-zA-Z 0-9!]+')])],
        confirmPassword:['',Validators.compose([Validators.required, Validators.pattern('[a-zA-Z 0-9!]+')])]
      });
  }

  get f() { return this.SingIn.controls; }

  onSubmit(event:any) {
    
  }

}
