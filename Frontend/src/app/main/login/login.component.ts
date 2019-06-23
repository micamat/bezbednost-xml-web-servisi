import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { UserService } from '../services/user.service';
import { Router, ActivatedRoute } from '@angular/router';
import { HttpHeaders } from '@angular/common/http';
import { HomeComponent } from '../home/home.component';
import { NavbarComponent } from '../navbar/navbar.component';

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
        username:['',Validators.compose([Validators.required, Validators.pattern('[a-zA-Z 0-9!]+')])],
        password:['',Validators.compose([Validators.required, Validators.pattern('[a-zA-Z 0-9!]+')])]
      });
  }

  get f() { return this.SingIn.controls; }

  onSubmit(event:any) {
    this.submitted = true;
    this.user = this.SingIn.getRawValue()
    // stop here if form is invalid
    if (this.SingIn.invalid) {
        console.log("USAOOOOOOOOO")
        console.log(this.SingIn.controls.username.hasError)
        console.log(this.SingIn.controls.username.get)
        return;
    }
    this._userService.login(this.user).subscribe(
      data =>{
              this.isLoged=true;
              localStorage.setItem('currentUser', JSON.parse(data).token);
              this.router.navigateByUrl('');
      }
    );
  }

 

}
