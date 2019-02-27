import {NgModule, Component} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {RegisterComponent} from './register/register.component';
import {AppComponent} from './app.component';
import {LoginComponent} from './login/login.component';
import {HomeComponent} from './home/home.component';
import {UserComponent} from './user/user.component';
import {TmComponent} from '../app/tm/tm.component';
import {AdminComponent} from './admin/admin.component';
import {MenuComponent} from './menu/menu.component';
import {CarsComponent} from './cars/cars.component';
import {UsersComponent} from './users/users.component';
import {InfoComponent} from './info/info.component';
import {CarProfileComponent} from './car-profile/car-profile.component';
import {UserProfileComponent} from './user-profile/user-profile.component';
import {ChartPageComponent} from './chart-page/chart-page.component';
import {BookingComponent} from './booking/booking.component';
import {SkillComponent} from './techservice/worker/skill/skill.component';
import {WorkerComponent} from './techservice/worker/worker.component';
import {TechserviceComponent} from './techservice/techservice.component';
import {TechmanagerProfileComponent} from './techmanager-profile/techmanager-profile.component';
import {NotificationsListComponent} from './notifications/notifications-list/notifications-list.component';
import {NotificationsApprovementComponent} from './notifications/notifications-approvement/notifications-approvement.component';
import {MapComponent} from './techservice/map/map.component';
import { CarTrackerComponent } from './cars/car-tracker/car-tracker.component';
import { CarDetailsComponent } from './cars/car-details/car-details.component';
import { ServicesFeedbackFormComponent } from './services-feedback-form/services-feedback-form.component'; 
import { WorkersFeedbackComponent } from './workers-feedback/workers-feedback.component';


const routes: Routes = [
  // { path: '**', redirectTo: 'auth/login'},
  
  {
    path: 'ui/feedback',
    component: ServicesFeedbackFormComponent
  },
  {
    path: 'ui/map',
    component: MapComponent
  },
  {
    path: 'ui/techmanager/profile',
    component: TechmanagerProfileComponent
  },
  {
    path: 'ui/techservice',
    component: TechserviceComponent
  },
  {
    path: 'ui/skills',
    component: SkillComponent
  },
  {
    path: 'ui/workers',
    component: WorkerComponent
  },
  {
    path: 'ui/home',
    component: AppComponent
    // component: MenuComponent
  },
  {
    path: 'ui/user',
    component: UserComponent
  },
  {
    path: 'ui/tm',
    component: TmComponent
  },
  {
    path: 'ui/admin',
    component: AdminComponent
  },
  {
    path: 'ui/auth/login',
    component: LoginComponent
  },
  {
    path: 'ui/signup',
    component: RegisterComponent
  },
  {
    path: '',
    redirectTo: 'ui/home',
    pathMatch: 'full'
  },
  {
    path: 'ui/charts',
    component: ChartPageComponent
  },

  {
    path: 'ui/ownercars/:id',
    component: CarsComponent
  },
  {
    path: 'ui/allusers',
    component: UsersComponent
  },

  {
    path: 'ui/booking/:id',
    component: BookingComponent
  },

  {
    path: 'ui/info',
    component: InfoComponent
  },

  {
    path: 'ui/newcar',
    component: CarProfileComponent
  },

  {
    path: 'ui/userprofile',
    component: UserProfileComponent
  },
  {
    path: 'ui/notifications-list/:id',
    component: NotificationsListComponent
  },
  {
    path: 'ui/notifications-approvement/:id',
    component: NotificationsApprovementComponent
  },
{
    path: 'ui/location',
    component: CarTrackerComponent
},

{
    path: 'ui/carprofile',
    component: CarDetailsComponent
}
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
