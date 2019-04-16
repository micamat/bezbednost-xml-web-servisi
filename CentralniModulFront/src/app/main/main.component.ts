import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { UserService } from '../services/user.services';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  tekst: any;

  constructor(private _userService : UserService,
              private router:Router,
              private activatedRoute: ActivatedRoute) { }

  ngOnInit() {

    this._userService.tekst().subscribe(
      data =>{
              this.tekst = data;
      }
    );
  }

}
