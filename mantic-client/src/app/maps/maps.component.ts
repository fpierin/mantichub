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
  dataSend: any;
  hasIdle: boolean = false;
  query: string;

  constructor( 
    private mapsService:MapsService,
    private route: ActivatedRoute
  ) {
    this.position = {lat: -23.5505734, lng: -46.6328059};
  }

  ngOnInit() {
  }

  ngOnDestroy(){
    this.location.unsubscribe();
  }




  /***************/
  /* FILTER FORM */
  /***************/

  onFilterSubmit(form){

    this.dataSend = { 
      "type": form.value.eventType || 'Event',
      "latitude": this.position.lat,
      "longitude": this.position.lng
     }

     this.getMarkers(this.dataSend);
     this.hasIdle = true;
  }



  /************/
  /* SERVICES */
  /************/

  getMarkers(dataSend){
    this.hasLoad = true;
    this.mapsService.getMarkers(dataSend)
      .then( (res:any) => {
        let markers = [];

        for ( let marker of res.resources ) {
          markers.push(this.getMarkerDetails(marker));
        }

        this.activedContent = 'result';
        this.markers = markers;
        this.query = res.sparqlQuery;
        this.hasLoad = false;
      })
      .catch( (err:any) =>  console.log(err))
  }

  onQuerySubmit(form){
      this.hasLoad = true;
      this.mapsService.getQuery({"query": form.value.query})
      .then( (res:any) => {
        let markers = [];

        for ( let marker of res.resources ) {
          markers.push(this.getMarkerDetails(marker));
        }

        this.markers = markers;
        this.activedContent = 'result';
        this.query = res.sparqlQuery;
        this.hasLoad = false;
      })
      .catch( (err:any) =>  console.log(err))
  }




  /***************/
  /* MAP ACTIONS */
  /***************/

  idle(){
    if(typeof this.position !== 'undefined' && this.hasIdle ){
      this.dataSend.latitude = this.position.lat;
      this.dataSend.longitude = this.position.lng;
      this.getMarkers(this.dataSend);
    }
  }

  markerClick(marker){
    this.backTo('result');
    this.detail = marker;
    this.showDetail = true;
  }

  /**********/
  /* OTHERS */
  /**********/

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

  backTo(content:string){
    if(content){
      this.activedContent = content;
      return;
    }
  }

  getDomain(url){
    let regex: RegExp = /https?:\/\/(?:www\.|)(.+?)(\/|\?)(.+?$)/;
    let match = url.match(regex);
    return match[1];
  }

  getMarkerDetails(marker){
    if(marker.url){
      marker.labelSite = this.getDomain(marker.url);
    }

    switch (marker.type) {
      case 'BarOrPub':
        marker.color = '741c8e';
        marker.icon = 'local_bar';
        break;
      case 'CivicStructure':
        marker.color = '49858f';
        marker.icon = 'add_location';
        break;
      case 'DanceEvent':
        marker.color = '991690';
        marker.icon = 'directions_walk';
        break;
      case 'Event':
        marker.color = '167bba';
        marker.icon = 'date_range';
        break;
      case 'ExhibitionEvent':
        marker.color = '49858f';
        marker.icon = 'visibility';
        break;
      case 'Festival':
        marker.color = '400ba5';
        marker.icon = 'album';
        break;
      case 'FoodEstablishment':
        marker.color = '910000';
        marker.icon = 'restaurant';
        break;
      case 'FoodEvent':
        marker.color = '35a7db';
        marker.icon = 'restaurant_menu';
        break;
      case 'MusicEvent':
        marker.color = 'e65b5e';
        marker.icon = 'album';
        break;
      case 'Restaurant':
        marker.color = '910000';
        marker.icon = 'restaurant';
        break;
      case 'Resource':
        marker.color = '678b99';
        marker.icon = 'local_bar';
        break;
      case 'ScreeningEvent':
        marker.color = '745fa5';
        marker.icon = 'local_movies';
        break;
      case 'SocialEvent':
        marker.color = 'b6d0c5';
        marker.icon = 'people';
        break;
      case 'TheaterEvent':
        marker.color = '0b9fa5';
        marker.icon = 'event_seat';
        break;
      case 'TrainStation':
        marker.color = '9e7b91';
        marker.icon = 'train';
        break;
      default:
        marker.color = '8cbf44';
        marker.icon = 'local_bar';
    }

    return marker;
  }

}