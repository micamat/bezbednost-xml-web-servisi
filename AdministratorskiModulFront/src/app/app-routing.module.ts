import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { AgentComponent } from './agent/agent.component';
import { AgentAddComponent } from './agent-add/agent-add.component';
import { AgentModifyComponent } from './agent-modify/agent-modify.component';

const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'agent', component: AgentComponent},
  {path: 'agent-add', component: AgentAddComponent},
  {path: 'agent-modify/:id', component: AgentModifyComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
