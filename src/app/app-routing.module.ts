import { NgModule, Component } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { RegisterComponent } from './register/register.component';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { UserComponent } from './user/user.component';
import { TmComponent } from '../app/tm/tm.component';
import { AdminComponent } from './admin/admin.component';
import { MenuComponent } from './menu/menu.component';
import {CarsComponent} from './cars/cars.component';
import {UsersComponent} from './users/users.component';
import {InfoComponent} from './info/info.component';
import {CarProfileComponent} from './car-profile/car-profile.component';
import { UserProfileComponent } from './user-profile/user-profile.component';

import { ChartPageComponent } from './chart-page/chart-page.component';
import { BookingComponent } from './booking/booking.component';
import { SkillComponent } from './techservice/worker/skill/skill.component';
import { WorkerComponent } from './techservice/worker/worker.component';
import { TechserviceComponent } from './techservice/techservice.component';
import { TechmanagerProfileComponent } from './techmanager-profile/techmanager-profile.component';
import { NotificationsListComponent } from './notifications/notifications-list/notifications-list.component';
import { NotificationsApprovementComponent } from './notifications/notifications-approvement/notifications-approvement.component';
import { MapComponent } from './techservice/map/map.component';
const routes: Routes = [
    // { path: '**', redirectTo: 'auth/login'},
    {
       path: 'map',
       component: MapComponent 
    },
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
    component: AppComponent
    // component: MenuComponent
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
{
    path: 'charts', 
component: ChartPageComponent},

{
    path: 'ownercars/:id',
    component: CarsComponent
},
{
    path: 'allusers',
    component: UsersComponent
},

{
    path : 'booking/:id',
    component : BookingComponent
},

{
    path: 'info',
    component: InfoComponent
},

{
    path: 'newcar',
    component: CarProfileComponent
},

{
    path: 'userprofile',
    component: UserProfileComponent
},
{
    path: 'notifications-list/:id',
    component: NotificationsListComponent
},
{
    path: 'notifications-approvement/:id',
    component: NotificationsApprovementComponent
}
]

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }
