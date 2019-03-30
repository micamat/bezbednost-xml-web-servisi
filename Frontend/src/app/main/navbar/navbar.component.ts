import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { FormBuilder } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  us:any;

  constructor(private _userService : UserService,
    private formBuilder:FormBuilder,
    private router:Router,
    private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
  }

  /*logout(){
    localStorage.clear();
  }*/
  ulogovani(){
    this._userService.getLoged().subscribe(
      data=>{
        this.us = data;
        return this.us;
      }
    )
  }
}
