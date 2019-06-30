import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { NavbarComponent } from './navbar/navbar.component';
import { AgentComponent } from './agent/agent.component';
import { AgentAddComponent } from './agent-add/agent-add.component';
import { AgentModifyComponent } from './agent-modify/agent-modify.component';
import { HttpClientModule } from '@angular/common/http'; 
import { ReactiveFormsModule } from '@angular/forms';
import { AccommodationTypeComponent } from './accommodation-type/accommodation-type.component';
import { AccomodationTypeAddComponent } from './accomodation-type-add/accomodation-type-add.component';
import { AccommodationTypeModifyComponent } from './accommodation-type-modify/accommodation-type-modify.component';
import { AccommodationCategoryComponent } from './accommodation-category/accommodation-category.component';
import { AccommodationCategoryAddComponent } from './accommodation-category-add/accommodation-category-add.component';
import { AccommodationCategoryModifyComponent } from './accommodation-category-modify/accommodation-category-modify.component';
import { AccessoryComponent } from './accessory/accessory.component';
import { AccessoryAddComponent } from './accessory-add/accessory-add.component';
import { AccessoryModifyComponent } from './accessory-modify/accessory-modify.component';
import { CommentComponent } from './comment/comment.component';
import { UserComponent } from './user/user.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    NavbarComponent,
    AgentComponent,
    AgentAddComponent,
    AgentModifyComponent,
    AccommodationTypeComponent,
    AccomodationTypeAddComponent,
    AccommodationTypeModifyComponent,
    AccommodationCategoryComponent,
    AccommodationCategoryAddComponent,
    AccommodationCategoryModifyComponent,
    AccessoryComponent,
    AccessoryAddComponent,
    AccessoryModifyComponent,
    CommentComponent,
    UserComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
