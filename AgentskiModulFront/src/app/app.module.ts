import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { HomeComponent } from './home/home.component';
import { RegistrationComponent } from './registration/registration.component';
import { LoginComponent } from './login/login.component';
import { FormGroup, ReactiveFormsModule , FormsModule} from '@angular/forms';
import { AccommodationComponent } from './accommodation/accommodation.component';
import { RoomComponent } from './room/room.component';
import { PricelistComponent } from './pricelist/pricelist.component';
import { AccommodationAddComponent } from './accommodation-add/accommodation-add.component';
import { HttpClientModule } from '@angular/common/http';
import { AccommodationChangeComponent } from './accommodation-change/accommodation-change.component';
import { RoomAddComponent } from './room-add/room-add.component';
import { RoomChangeComponent } from './room-change/room-change.component';
import { PricelistAddComponent } from './pricelist-add/pricelist-add.component';
import { PricelistChangeComponent } from './pricelist-change/pricelist-change.component';
import { CommentComponent } from './comment/comment.component';
import { ReservationComponent } from './reservation/reservation.component';
import { ReservationShowComponent } from './reservation-show/reservation-show.component';
import { MessageComponent } from './message/message.component';
import { ReservationRoomsComponent } from './reservation-rooms/reservation-rooms.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    LoginComponent,
    RegistrationComponent,
    HomeComponent,
    AccommodationComponent,
    RoomComponent,
    PricelistComponent,
    AccommodationAddComponent,
    AccommodationChangeComponent,
    RoomAddComponent,
    RoomChangeComponent,
    PricelistAddComponent,
    PricelistChangeComponent,
    CommentComponent,
    ReservationComponent,
    ReservationShowComponent,
    MessageComponent,
    ReservationRoomsComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
