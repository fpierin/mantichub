import { Component, OnInit, OnDestroy } from '@angular/core';
import { NgModule }      from '@angular/core';

import { MapsService } from './../shared/services/mantic/mantic.service';
import { LatLng, LatLngLiteral } from '@agm/core';
import { Subscription } from "rxjs/Subscription";
import { ActivatedRoute } from "@angular/router";


@Component({
  selector: 'app-maps',
  templateUrl: './maps.component.html',
  styleUrls: ['./maps.component.scss']
})
export class MapsComponent implements OnInit, OnDestroy {
  
  detail: any;
  location: any;
  markers: Object;
  zoom: number = 15;
  showDetail: boolean;
  geolocation: Subscription;
  position: LatLngLiteral;
  hasLoad: boolean = false;
  activedContent: string = "filter";

  constructor( 
    private mapsService:MapsService,
    private route: ActivatedRoute
  ) {

    this.geolocation = this.route.data.subscribe(
      (info) => {
        this.position = info.geolocation;
      }
    );

  }

  ngOnInit() {
  }

  ngOnDestroy(){
    this.location.unsubscribe();
  }

  /* FILTER FORM */

  onFilterSubmit(form){

    let dataSend = { 
      "type": form.value.eventType || 'Event',
      "latitude": this.position.lat,
      "longitude": this.position.lng
     }

     this.getMarkers(dataSend);
  }

  getMarkers(dataSend){
    this.hasLoad = true;
    this.mapsService.getMarkers(dataSend)
      .then( (res:any) => {
        this.activedContent = 'result';
        this.markers = res.resources;
        this.hasLoad = false;
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

  centerChange(e){
    this.position = e;
  }

  idle(){
    if(typeof this.position !== 'undefined'){
      //this.getMarkers();
    }
  }

  backTo(content:string){

    if(content){
      this.activedContent = content;
      return;
    }
    
  }

}