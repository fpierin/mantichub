import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { AgmCoreModule } from 'angular2-google-maps/core';
import { SharedModule } from './../shared/shared.module';
import { MapsComponent } from './maps.component';

@NgModule({
  declarations: [MapsComponent],
  imports: [
    CommonModule,
    SharedModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyA00uWlZW9Pu7Xmfl8S5GJQYtAOi-Io0lg'
    })
  ],
  exports:[MapsComponent],
  providers: []
})
export class MapsModule { }
