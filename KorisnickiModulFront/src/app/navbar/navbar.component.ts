import { Component, OnInit } from '@angular/core';
import { Shared } from '../services/Token';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(private tandu: Shared) { }

  ngOnInit() {
            this.tandu.username = localStorage.getItem('username');
            this.tandu.token = localStorage.getItem('token');
  }

  logout(){
    localStorage.setItem('token', 'undefined');   
    localStorage.setItem('username', 'undefined');
    this.tandu.token = 'undefined';
    this.tandu.username = 'undefined';
    
  }


}

