import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { HomeComponent } from './home/home.component';
import { AccommodationListComponent } from './accommodation-list/accommodation-list.component';

const routes: Routes = [
  {path : '', component: HomeComponent},
  {path : 'login', component : LoginComponent},
  {path : 'register', component : RegistrationComponent},
  {path : 'accommodation-list',component : AccommodationListComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
