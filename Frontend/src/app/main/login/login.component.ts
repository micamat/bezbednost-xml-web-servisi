import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { UserService } from '../services/user.service';
import { Router, ActivatedRoute } from '@angular/router';

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
    this._userService.login(this.user).subscribe(
      data =>{
              this.router.navigateByUrl("")
              this.isLoged=true;
              console.log(this.isLoged);
      }
    );

    this._userService.getLoged().subscribe(
      data=>{
        console.log(data);
      }
    )


  }

}
