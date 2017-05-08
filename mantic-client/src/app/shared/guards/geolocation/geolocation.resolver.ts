import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, RouterStateSnapshot, Resolve } from "@angular/router/src";

import { Observable } from 'rxjs/Observable';
import { LatLngLiteral, LatLng } from '@agm/core';
import { GeolocationService } from './../../services/geolocation/geolocation.service';

@Injectable()
export class GeolocationResolver implements Resolve<any>{

  constructor(
    private geolocationService: GeolocationService
  ) {}
  
  resolve(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
    ): Observable<any>|Promise<any>|any {
      return this.geolocationService.getCurrentPosition();
  }

}
