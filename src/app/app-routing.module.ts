import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
 
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { UserComponent } from './user/user.component';
import { TmComponent } from '../app/tm/tm.component';
import { AdminComponent } from './admin/admin.component';

const routes: Routes = [
  {
    path: 'home',
    component: HomeComponent
},
{
    path: 'user',
    component: UserComponent
},
{
    path: 'tm',
    component: TmComponent
},
{
    path: 'admin',
    component: AdminComponent
},
{
    path: 'auth/login',
    component: LoginComponent
},
{
    path: 'signup',
    component: RegisterComponent
},
{
    path: '',
    redirectTo: 'auth/login',
    pathMatch: 'full'
}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
