import { Component, OnInit } from '@angular/core';
import {  FormGroup, FormBuilder, Validators } from '@angular/forms';
import { UserService } from '../services/user.service';
import { Router, ActivatedRoute } from '@angular/router';
import { HttpHeaders } from '@angular/common/http';

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

  constructor(private _userService : UserService,
    private formBuilder:FormBuilder,
    private router:Router,
    private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
      this.SingIn = this.formBuilder.group({
        email:['',Validators.required],
        password:['',Validators.required]
      });
  }

  get f() { return this.SingIn.controls; }

  onSubmit(event:any) {
    this.submitted = true;
    this.user = this.SingIn.getRawValue()
    // stop here if form is invalid
    if (this.SingIn.invalid) {
        return;
    }

    console.log(this.user)
    console.log(this.user.email)

    this._userService.login(this.user).subscribe(
      data =>{
              console.log(data);
              if (data == 'true'){
                this.isLoged=true;
              //window.location.href = 'putanja do komponente';
              //this.router.navigateByUrl('/loggedIn');
                window.location.href= "assets/loggedin.html";
              } else {
                this.isLoged=false;
              }
              localStorage.setItem('currentUser', JSON.parse(data).token);
      }
    );
  }

 

}
