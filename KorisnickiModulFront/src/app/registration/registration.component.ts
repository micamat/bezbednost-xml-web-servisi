import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  RegisterIn:FormGroup;
  submitted = false;
  user: any;
  public isLoged:boolean=false;
  us:any;
  temp:any;

  constructor(
    private _userService : UserService,
    private formBuilder : FormBuilder,
    private router:Router,
    private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
      this.RegisterIn = this.formBuilder.group({
        ime:['',Validators.compose([Validators.required, Validators.pattern('[a-zA-Z 0-9!]+')])],
        prezime:['',Validators.compose([Validators.required, Validators.pattern('[a-zA-Z 0-9!]+')])],
        email:['',Validators.compose([Validators.required, Validators.email])],
        telefon:['',Validators.compose([Validators.required, Validators.pattern('[-0-9!]+')])],
        sifra:['',Validators.compose([Validators.required, Validators.pattern('[a-zA-Z 0-9!]+')])],
        aktivan:['',Validators.compose(null)],
        korisnickoIme:['',Validators.compose([Validators.required, Validators.pattern('[a-zA-Z 0-9!]+')])],
        idUloga:['',Validators.compose([Validators.required, Validators.pattern('[0-9!]+')])],
      });
  }

  get f() { return this.RegisterIn.controls; }

  onSubmit(event:any) {
    this.submitted = true;
    this.temp = this.RegisterIn.getRawValue();
    this.temp.aktivan = true;
    this.temp.idUloga = 1;
    console.log(this.temp)
    this._userService.registration(this.temp).subscribe(
      data => {
              this.router.navigateByUrl("");
    });
  }
}
