import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  users : any;

  constructor(
    private agentService: UserService,
    private router:Router
  ) { }

  refresh(){
    this.agentService.getAll().subscribe(
      data => {
        this.users = data;
      }
    );
  }

  ngOnInit() {
    this.refresh();
  }

  block(id: any){
    this.agentService.block(id).subscribe(
      data => {
        this.refresh();
      }
    );
  }

}
