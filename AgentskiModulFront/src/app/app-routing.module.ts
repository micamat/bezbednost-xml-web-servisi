import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { AccommodationComponent } from './accommodation/accommodation.component';
import { RoomComponent } from './room/room.component';
import { OcupationComponent } from './ocupation/ocupation.component';
import { PricelistComponent } from './pricelist/pricelist.component';
import { AccommodationAddComponent } from './accommodation-add/accommodation-add.component';
import { AccommodationChangeComponent } from './accommodation-change/accommodation-change.component';
import { RoomAddComponent } from './room-add/room-add.component';
import { RoomChangeComponent } from './room-change/room-change.component';
import { OcupationAddComponent } from './ocupation-add/ocupation-add.component';
import { OcupationChangeComponent } from './ocupation-change/ocupation-change.component';
import { PricelistAddComponent } from './pricelist-add/pricelist-add.component';
import { PricelistChangeComponent } from './pricelist-change/pricelist-change.component';

const routes: Routes = [
  {path : '', component: HomeComponent},
  {path : 'login', component : LoginComponent},
  {path : 'register', component : RegistrationComponent},
  {path : 'accommodation', component : AccommodationComponent},
  {path : 'accommodation/add', component : AccommodationAddComponent},
  {path : 'accommodation/change/:id', component : AccommodationChangeComponent},
  {path : 'room', component : RoomComponent},
  {path : 'room/add', component : RoomAddComponent},
  {path : 'room/change/:id', component : RoomChangeComponent},
  {path : 'ocupation', component : OcupationComponent},
  {path : 'ocupation/add', component : OcupationAddComponent},
  {path : 'ocupation/change/:id', component : OcupationChangeComponent},
  {path : 'pricelist', component : PricelistComponent},
  {path : 'pricelist/add', component : PricelistAddComponent},
  {path : 'pricelist/change/:id', component : PricelistChangeComponent},
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
