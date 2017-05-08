import { NgModule } from '@angular/core';
import { FormsModule } from "@angular/forms";
import { CommonModule } from '@angular/common';

import { AgmMap, AgmMarker } from '@agm/core';
import { AgmCoreModule } from '@agm/core';
import { MapsService } from './../shared/services/mantic/mantic.service';
import { EllipsisPipe } from './../pipes/ellipsis.pipe';
import { MapsComponent } from './maps.component';
import { GeolocationResolver } from './../shared/guards/geolocation/geolocation.resolver';
import { GeolocationService } from './../shared/services/geolocation/geolocation.service';

@NgModule({
  declarations: [
    MapsComponent,
    EllipsisPipe
  ],
  imports: [
    CommonModule,
    FormsModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyA00uWlZW9Pu7Xmfl8S5GJQYtAOi-Io0lg'
    })
  ],
  providers: [
    MapsService,
    GeolocationResolver,
    GeolocationService
  ]
})
export class MapsModule { }
