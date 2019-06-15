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

const routes: Routes = [
  {path : '', component: HomeComponent},
  {path : 'login', component : LoginComponent},
  {path : 'register', component : RegistrationComponent},
  {path : 'accommodation', component : AccommodationComponent},
  {path : 'accommodation/add', component : AccommodationAddComponent},
  {path : 'accommodation/change/:id', component : AccommodationChangeComponent},
  {path : 'room', component : RoomComponent},
  {path : 'ocupation', component : OcupationComponent},
  {path : 'pricelist', component : PricelistComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
