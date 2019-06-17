import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { HomeComponent } from './home/home.component';
import { RegistrationComponent } from './registration/registration.component';
import { LoginComponent } from './login/login.component';
import { FormGroup, ReactiveFormsModule } from '@angular/forms';
import { AccommodationComponent } from './accommodation/accommodation.component';
import { RoomComponent } from './room/room.component';
import { OcupationComponent } from './ocupation/ocupation.component';
import { PricelistComponent } from './pricelist/pricelist.component';
import { AccommodationAddComponent } from './accommodation-add/accommodation-add.component';
import { HttpClientModule } from '@angular/common/http';
import { AccommodationChangeComponent } from './accommodation-change/accommodation-change.component';
import { RoomAddComponent } from './room-add/room-add.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    LoginComponent,
    RegistrationComponent,
    HomeComponent,
    AccommodationComponent,
    RoomComponent,
    OcupationComponent,
    PricelistComponent,
    AccommodationAddComponent,
    AccommodationChangeComponent,
    RoomAddComponent
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
