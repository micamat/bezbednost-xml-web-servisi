import { Component, OnInit } from '@angular/core';
import { AgentService } from '../services/agent.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-agent',
  templateUrl: './agent.component.html',
  styleUrls: ['./agent.component.css']
})
export class AgentComponent implements OnInit {

  agents: any;

  constructor(
    private agentService: AgentService,
    private router:Router
  ) { }

  refresh(){
    this.agentService.getAll().subscribe(
      data => {
        this.agents = data;
      }
    );
  }

  ngOnInit() {
    this.refresh();
  }

  delete(id: any){
    this.agentService.delete(id).subscribe(
      data => {
        this.refresh();
      }
    );
  }

}
