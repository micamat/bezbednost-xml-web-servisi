import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { FormBuilder } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  us:any = false;

  constructor(private _userService : UserService,
    private formBuilder:FormBuilder,
    private router:Router,
    private activatedRoute: ActivatedRoute) { }

    ngOnInit() {
    let aut = localStorage.getItem('currentUser')
    console.log(aut)
    if(aut != null){
     
      this.us = true;
      console.log(this.us + "Ima ulogovanog")
      const httpOptions = {
        headers: new HttpHeaders({
          'Content-Type':  'application/json',
          'Authorization': aut
        })
      };
    }else{
      console.log(this.us + "Nemaa ulogovanog")
      this.us = false;
    }

  

    
  }

  logout(){
    localStorage.clear();
    this.us = false;
    this.router.navigateByUrl("");
  }
}
