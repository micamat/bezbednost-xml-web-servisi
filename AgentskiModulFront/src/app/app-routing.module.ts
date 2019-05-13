import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LoginComponent } from './main/login/login.component';
import { LoggedInComponent } from './main/loggedIn/loggedIn.component';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { LoggedInService } from "./main/services/loggedIn.service";

const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'loggedIn', component: LoggedInComponent}
];

@NgModule({
  declarations: [LoginComponent, LoggedInComponent],
  imports: [
    RouterModule.forRoot(routes),
    FormsModule,
    ReactiveFormsModule,
    CommonModule,
    HttpClientModule
  ],
  providers: [LoggedInService],
  entryComponents: [LoggedInComponent],
  exports: [RouterModule, LoginComponent]
})
export class AppRoutingModule { }
