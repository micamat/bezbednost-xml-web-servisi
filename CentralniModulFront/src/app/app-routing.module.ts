import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MainComponent } from './main/main.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';

const routes: Routes = [
  {path: '', component: MainComponent},
];

@NgModule({
  imports: [
      RouterModule.forRoot(routes),
      FormsModule,
      ReactiveFormsModule,
      CommonModule,
      HttpClientModule
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
