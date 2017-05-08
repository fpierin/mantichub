import { Injectable } from '@angular/core';

import { LatLng,LatLngLiteral } from '@agm/core';
import { config } from './../../../app.config';

@Injectable()
export class GeolocationService {

  constructor() { }

  getCurrentPosition():Promise<LatLngLiteral>{
    return new Promise( 
      (resolve, reject) =>{
        let hasSupport: Boolean = this.checkBrowserSupport();
        
        if(hasSupport){
          navigator.geolocation.getCurrentPosition((location:any) => {
            resolve({
              lat : location.coords.latitude,
              lng : location.coords.longitude
            });
          });
        }else{
          reject(config.initialPosition);
        }

      }
    );
  }

  checkBrowserSupport():Boolean{
    if(navigator.geolocation) return true;
    return false;
  }

}