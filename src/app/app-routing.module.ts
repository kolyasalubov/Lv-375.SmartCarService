import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ChartPageComponent } from './chart-page/chart-page.component';

const routes: Routes = [
  {path: 'charts', component: ChartPageComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
