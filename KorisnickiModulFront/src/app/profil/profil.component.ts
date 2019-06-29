import { Component, OnInit } from '@angular/core';
import { Shared } from '../services/Token';

@Component({
  selector: 'app-profil',
  templateUrl: './profil.component.html',
  styleUrls: ['./profil.component.css']
})
export class ProfilComponent implements OnInit {

  constructor(private tandu: Shared) { }

  ngOnInit() {
  }

}
