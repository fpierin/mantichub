import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { AgmMap, AgmMarker } from '@agm/core';
import { AgmCoreModule } from '@agm/core';
import { MapsService } from './../services/maps.service';
import { EllipsisPipe } from './../pipes/ellipsis.pipe';
import { MapsComponent } from './maps.component';

@NgModule({
  declarations: [
    MapsComponent,
    EllipsisPipe
  ],
  imports: [
    CommonModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyA00uWlZW9Pu7Xmfl8S5GJQYtAOi-Io0lg'
    })
  ],
  providers: [MapsService]
})
export class MapsModule { }
