import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { HttpHeaders } from '@angular/common/http';
//import { HomeComponent } from '../home/home.component';
import { NavbarComponent } from '../navbar/navbar.component';
import { UserService } from '../services/user.service';
import { Shared } from '../services/Token';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  SingIn:FormGroup;
  submitted = false;
  user: any;
  public isLoged:boolean=false;
  us:any;
  uAt:any;
  temp:any;

  constructor(
    private _userService : UserService,
    private formBuilder:FormBuilder,
    private router:Router,
    private activatedRoute: ActivatedRoute,
    private tandu: Shared) { }

  ngOnInit() {
      this.SingIn = this.formBuilder.group({
        username:['',Validators.compose([Validators.required, Validators.pattern('[a-zA-Z 0-9!]+')])],
        password:['',Validators.compose([Validators.required, Validators.pattern('[a-zA-Z 0-9!]+')])]
      });
  }

  get f() { return this.SingIn.controls; }

  onSubmit(event:any) {
    this.submitted = true;
    this.temp = this.SingIn.getRawValue();
    console.log(this.temp)
    this._userService.login(this.temp).subscribe(
      data => {
        this.uAt = JSON.parse(data);
        this.tandu.token = this.uAt.token;
        this.tandu.username = this.uAt.username;
        localStorage.setItem('token',this.uAt.token);
        localStorage.setItem('username',this.uAt.username);
        this.router.navigateByUrl("");
    });
  }


}
