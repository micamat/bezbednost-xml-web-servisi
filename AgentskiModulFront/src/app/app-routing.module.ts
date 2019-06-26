import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { AccommodationComponent } from './accommodation/accommodation.component';
import { RoomComponent } from './room/room.component';
import { PricelistComponent } from './pricelist/pricelist.component';
import { AccommodationAddComponent } from './accommodation-add/accommodation-add.component';
import { AccommodationChangeComponent } from './accommodation-change/accommodation-change.component';
import { RoomAddComponent } from './room-add/room-add.component';
import { RoomChangeComponent } from './room-change/room-change.component';
import { PricelistAddComponent } from './pricelist-add/pricelist-add.component';
import { PricelistChangeComponent } from './pricelist-change/pricelist-change.component';
import { CommentComponent } from './comment/comment.component';
import { ReservationComponent } from './reservation/reservation.component';
import { ReservationShowComponent } from './reservation-show/reservation-show.component';
import { MessageComponent } from './message/message.component';

const routes: Routes = [
  {path : '', component: HomeComponent},
  {path : 'login', component : LoginComponent},
  {path : 'register', component : RegistrationComponent},
  {path : 'accommodation', component : AccommodationComponent},
  {path : 'accommodation/add', component : AccommodationAddComponent},
  {path : 'accommodation/change/:id', component : AccommodationChangeComponent},
  {path : 'accommodation/reservation/:id', component : ReservationComponent},
  {path : 'room', component : RoomComponent},
  {path : 'room/add', component : RoomAddComponent},
  {path : 'room/change/:id', component : RoomChangeComponent},
  {path : 'pricelist', component : PricelistComponent},
  {path : 'pricelist/add', component : PricelistAddComponent},
  {path : 'pricelist/change/:id', component : PricelistChangeComponent},
  {path : 'comment', component : CommentComponent},
  {path : 'reservation', component : ReservationShowComponent},
  {path : 'reservation/message/:id', component : MessageComponent},
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
