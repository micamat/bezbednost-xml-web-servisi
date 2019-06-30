import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { HomeComponent } from './home/home.component';
import { ProfilComponent } from './profil/profil.component';
import { PasswordChangeComponent } from './password-change/password-change.component';
import { HotelsComponent } from './hotels/hotels.component';

const routes: Routes = [
  {path : '', component: HomeComponent},
  {path : 'login', component : LoginComponent},
  {path : 'register', component : RegistrationComponent},
  {path : 'profil', component: ProfilComponent},
  {path : 'change', component: PasswordChangeComponent},
  {path : 'hotel', component: HotelsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
