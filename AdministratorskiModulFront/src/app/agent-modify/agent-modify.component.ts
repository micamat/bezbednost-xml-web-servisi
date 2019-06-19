import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AgentService } from '../services/agent.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-agent-modify',
  templateUrl: './agent-modify.component.html',
  styleUrls: ['./agent-modify.component.css']
})
export class AgentModifyComponent implements OnInit {

  agentAdd: FormGroup;
  agent: any;
  agentId: any;
  current: any;

  constructor(
    private agentService: AgentService,
    private formBuilder:FormBuilder,
    private router:Router,
    private activatedRoute: ActivatedRoute
  ) { }

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(
      params => {
          this.agentId = params.get('id');

          this.agentService.getById(this.agentId).subscribe(
              data => {
                this.agent = data;
                this.agentAdd = this.formBuilder.group({
                  ime:[this.agent.ime,Validators.compose([Validators.required, Validators.pattern('[a-zA-Z 0-9!]+')])],
                  prezime:[this.agent.prezime,Validators.compose([Validators.required, Validators.pattern('[a-z.A-Z 0-9!]+')])],
                  adresa:[this.agent.adresa,Validators.compose([Validators.required, Validators.pattern('[a-zA-Z 0-9!]+')])],
                  poslovniMaticniBroj:[this.agent.poslovniMaticniBroj,Validators.compose([Validators.required, Validators.pattern('[a-z.A-Z 0-9!]+')])],  
                  sifra:[this.agent.sifra,Validators.compose([Validators.required, Validators.pattern('[a-zA-Z 0-9!]+')])]
                });
              }
          );
      }
    );
  }

  onSubmit(event:any) {
    this.current = this.agentAdd.getRawValue();
    this.agentService.update(this.current, this.agentId).subscribe(
      data => {
        this.router.navigateByUrl("agent");
    });
  }

}
