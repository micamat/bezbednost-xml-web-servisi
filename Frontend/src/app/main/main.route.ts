import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { AdminPageComponent } from './admin-page/admin-page.component';
import { CreateCertificateComponent } from './create-certificate/create-certificate.component';
import { CreateNodeComponent } from './create-node/create-node.component';
import { CreateKeystoreComponent } from './create-keystore/create-keystore.component';
import { RegisterComponent } from "./register/register.component";
import { ShowCertificatesComponent } from './show-certificates/show-certificates.component';
import { DistributionComponent } from './distribution/distribution.component';

export const MainRoutes=[
    {path : '', component: HomeComponent},
    {path: 'adminPage', component: AdminPageComponent},
    {path: 'register', component:RegisterComponent},
    {path: 'login', component: LoginComponent},
    {path: 'adminPage/createCert', component: CreateCertificateComponent},
    {path: 'adminPage/createNode', component: CreateNodeComponent},
    {path: 'adminPage/createKeystore', component: CreateKeystoreComponent},
    {path: 'certificates', component: ShowCertificatesComponent},
    {path: 'distribution', component: DistributionComponent}
]
