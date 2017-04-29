import { Component, OnInit } from '@angular/core';

import { MapsService } from './../services/maps.service';
import { MapsAPILoader } from '@agm/core';
declare var google: any;

@Component({
  selector: 'app-maps',
  templateUrl: './maps.component.html',
  styleUrls: ['./maps.component.scss']
})
export class MapsComponent implements OnInit {
  
  showDetail: boolean;
  markers: Object;
  zoom: number = 15;
  location: any;
  detail: any;
  initPos: any = {
    lat: -23.5522016,
    lng: -46.6354753   
  };

  constructor( private mapsService:MapsService ) {}

  ngOnInit() {
    this.location = this.getGeoLocation();
  }


  getGeoLocation(){
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition((location) => {
        this.initPos.lat = location.coords.latitude;
        this.initPos.lng = location.coords.longitude;
      });
    }
  }

  getMarkers(){
    this.mapsService.getMarkers({
       "latitude": this.initPos.lat,
       "longitude": this.initPos.lng
      })
      .then( (res:any) => {
        this.markers = res.resources
        console.log(this.markers);
      })
      .catch( (err:any) =>  console.log(err))
    
  }

  openDetail(marker){
    this.detail = marker;
    this.showDetail = true;
  }

  hiddenDetail(){
    this.showDetail = false;
  }

}
