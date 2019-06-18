import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { AgentComponent } from './agent/agent.component';
import { AgentAddComponent } from './agent-add/agent-add.component';
import { AgentModifyComponent } from './agent-modify/agent-modify.component';
import { AccommodationTypeComponent } from './accommodation-type/accommodation-type.component';
import { AccomodationTypeAddComponent } from './accomodation-type-add/accomodation-type-add.component';
import { AccommodationTypeModifyComponent } from './accommodation-type-modify/accommodation-type-modify.component';
import { AccommodationCategoryComponent } from './accommodation-category/accommodation-category.component';
import { AccommodationCategoryAddComponent } from './accommodation-category-add/accommodation-category-add.component';
import { AccommodationCategoryModifyComponent } from './accommodation-category-modify/accommodation-category-modify.component';
import { AccessoryComponent } from './accessory/accessory.component';
import { AccessoryAddComponent } from './accessory-add/accessory-add.component';
import { AccessoryModifyComponent } from './accessory-modify/accessory-modify.component';

const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'agent', component: AgentComponent},
  {path: 'agent-add', component: AgentAddComponent},
  {path: 'agent-modify/:id', component: AgentModifyComponent},
  {path: 'accommodation-type', component: AccommodationTypeComponent},
  {path: 'accommodation-type-add', component: AccomodationTypeAddComponent},
  {path: 'accommodation-type-modify/:id', component: AccommodationTypeModifyComponent},
  {path: 'accommodation-category', component: AccommodationCategoryComponent},
  {path: 'accommodation-category-add', component: AccommodationCategoryAddComponent},
  {path: 'accommodation-category-modify/:id', component: AccommodationCategoryModifyComponent},
  {path: 'accessory', component: AccessoryComponent},
  {path: 'accessory-add', component: AccessoryAddComponent},
  {path: 'accessory-modify/:id', component: AccessoryModifyComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
