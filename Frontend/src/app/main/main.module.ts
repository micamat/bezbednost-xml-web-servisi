import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MainComponent } from './main.component';
import {MatToolbarModule, MatButtonModule, MatIconModule, MatFormFieldModule, MatInputModule, MatTableModule, MatCardModule} from '@angular/material';
import {RouterModule} from '@angular/router';
import { MainRoutes } from './main.route';
import { SharedModule } from '../shared/shared.module';
import {FlexLayoutModule} from '@angular/flex-layout';
import { NavbarComponent } from './navbar/navbar.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { AdminPageComponent } from './admin-page/admin-page.component';
import { CreateCertificateComponent } from './create-certificate/create-certificate.component';
import { CreateNodeComponent } from './create-node/create-node.component';
import { CreateKeystoreComponent } from './create-keystore/create-keystore.component';
import { ShowCertificatesComponent } from './show-certificates/show-certificates.component';
import { RegisterComponent } from './register/register.component';


@NgModule({
  declarations:[
    MainComponent,
    NavbarComponent,
    HomeComponent,
    LoginComponent,
    AdminPageComponent,
    CreateCertificateComponent,
    CreateNodeComponent,
    CreateKeystoreComponent,
    ShowCertificatesComponent,
    RegisterComponent,
  ],
  imports: [
    CommonModule,
    RouterModule.forRoot(MainRoutes),
    SharedModule,
    NgbModule.forRoot(),
    FlexLayoutModule,
    FormsModule,
    ReactiveFormsModule,
    MatInputModule,
    MatButtonModule, 
    MatCardModule, 
    MatTableModule, 
    MatFormFieldModule
  ],
  entryComponents:[],
  exports: [MainComponent]
})
export class MainModule { }
