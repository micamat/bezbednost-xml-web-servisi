import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AccommodationService } from '../services/accommodation.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Shared } from '../services/Token';

@Component({
  selector: 'app-password-change',
  templateUrl: './password-change.component.html',
  styleUrls: ['./password-change.component.css']
})
export class PasswordChangeComponent implements OnInit {

  SingIn:FormGroup;
  submitted = false;
  user: any;
  public isLoged:boolean=false;
  uAt: any;

  constructor(
    private _accommodationService : AccommodationService,
    private formBuilder:FormBuilder,
    private router:Router,
    private activatedRoute: ActivatedRoute,
    private tandu: Shared) { }

  ngOnInit() {
      this.SingIn = this.formBuilder.group({
        korisnickoIme:['',Validators.compose([Validators.required, Validators.pattern('[a-zA-Z 0-9!]+')])],
        lozinka:['',Validators.compose([Validators.required, Validators.pattern('[a-zA-Z 0-9!]+')])],
        prethodnaLozinka:['',Validators.compose([Validators.required, Validators.pattern('[a-zA-Z 0-9!]+')])]
      });
  }

  get f() { return this.SingIn.controls; }

  onSubmit(event:any) {
    this.submitted = true;
    this.user = this.SingIn.getRawValue();

    this._accommodationService.changePass(this.user).subscribe(
      data => {
        this.uAt = data
        this.tandu.token = 'undefined';
        this.tandu.username = 'undefined';
        localStorage.setItem('token', 'undefined');
        localStorage.setItem('username', 'undefined');
        this.router.navigateByUrl("");
        
    });
  }

}
