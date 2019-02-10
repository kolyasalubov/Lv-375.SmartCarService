import { NgModule, Component } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { UserComponent } from './user/user.component';
import { TmComponent } from '../app/tm/tm.component';
import { AdminComponent } from './admin/admin.component';

import { ChartPageComponent } from './chart-page/chart-page.component';
import { SkillComponent } from './techservice/worker/skill/skill.component';
import { WorkerComponent } from './techservice/worker/worker.component';
import { TechserviceComponent } from './techservice/techservice.component';
import { TechmanagerProfileComponent } from './techmanager-profile/techmanager-profile.component';

const routes: Routes = [
    {
        path: 'techmanager/profile',
        component: TechmanagerProfileComponent
    },
    {
        path: 'techservice',
        component: TechserviceComponent
    },
    {
        path: 'skills',
        component: SkillComponent
    },
    {
        path: 'workers',
        component: WorkerComponent
    }, 
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
        redirectTo: 'home',
        pathMatch: 'full'
    },
    
    {path: 'charts', component: ChartPageComponent}
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }
