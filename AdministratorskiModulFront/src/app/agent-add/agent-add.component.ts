import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AgentService } from '../services/agent.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-agent-add',
  templateUrl: './agent-add.component.html',
  styleUrls: ['./agent-add.component.css']
})
export class AgentAddComponent implements OnInit {

  agentAdd: FormGroup;
  agent: any;

  constructor(
    private agentService: AgentService,
    private formBuilder:FormBuilder,
    private router:Router,
    private activatedRoute: ActivatedRoute
  ) { }

  ngOnInit() {
    this.agentAdd = this.formBuilder.group({
      ime:['',Validators.compose([Validators.required, Validators.pattern('[a-zA-Z 0-9!]+')])],
      prezime:['',Validators.compose([Validators.required, Validators.pattern('[a-z.A-Z 0-9!]+')])],
      adresa:['',Validators.compose([Validators.required, Validators.pattern('[a-zA-Z 0-9!]+')])],
      poslovniMaticniBroj:['',Validators.compose([Validators.required, Validators.pattern('[a-z.A-Z 0-9!]+')])],  
      sifra:['',Validators.compose([Validators.required, Validators.pattern('[a-zA-Z 0-9!]+')])]
    });
  }

  onSubmit(event: any){
    this.agent = this.agentAdd.getRawValue();
    this.agentService.create(this.agent).subscribe(
      data => {
        this.router.navigateByUrl("agent");
      }
    );
  }

}
