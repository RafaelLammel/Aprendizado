import { NgModule } from '@angular/core';
import { NavBarComponent } from './component/navbar/navbar.component';
import { RouterModule } from '@angular/router';
import { Error404Component } from './component/error404/error404.component';

@NgModule({
  declarations: [
    NavBarComponent,
    Error404Component
  ],
  imports: [
    RouterModule.forChild([
      {
        path: '**', component: Error404Component
      }
    ])
  ],
  exports: [
    NavBarComponent
  ]
})
export class CoreModule {

}