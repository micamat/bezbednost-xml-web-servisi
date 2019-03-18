import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { AdminPageComponent } from './admin-page/admin-page.component';
import { CreateCertificateComponent } from './create-certificate/create-certificate.component';

export const MainRoutes=[
    {path : '', component: HomeComponent},
    {path: 'adminPage', component: AdminPageComponent},
    {path: 'login', component: LoginComponent},
    {path: 'adminPage/createCert', component: CreateCertificateComponent},
    
]
