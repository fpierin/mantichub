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
        let id = 1;

        for ( let marker of res.resources ) {
          marker.id = id;
          markers.push(this.getMarkerDetails(marker));
          id++;
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
        this.activedContent = 'result';
        this.markers = res.resources;
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

  getMarkerDetails(marker){

    switch (marker.type) {
      case 'BarOrPub':
        marker.color = '741c8e';
        marker.icon = 'local_bar';
        break;
      case 'CivicStructure':
        marker.color = '999999';
        marker.icon = 'train';
        break;
      case 'DanceEvent':
        marker.color = '991690';
        marker.icon = 'local_bar';
        break;
      case 'Event':
        marker.color = '167bba';
        marker.icon = 'local_bar';
        break;
      case 'ExhibitionEvent':
        marker.color = 'CCC';
        marker.icon = 'local_bar';
        break;
      case 'Festival':
        marker.color = '400ba5';
        marker.icon = 'local_bar';
        break;
      case 'FoodEstablishment':
        marker.color = '910000';
        marker.icon = 'restaurant';
        break;
      case 'FoodEvent':
        marker.color = '910000';
        marker.icon = 'restaurant_menu';
        break;
      case 'MusicEvent':
        marker.color = '400ba5';
        marker.icon = 'local_bar';
        break;
      case 'Restaurant':
        marker.color = '910000';
        marker.icon = 'restaurant';
        break;
      case 'Resource':
        marker.color = 'CCC';
        marker.icon = 'local_bar';
        break;
      case 'ScreeningEvent':
        marker.color = 'CCC';
        marker.icon = 'live_tv';
        break;
      case 'SocialEvent':
        marker.color = 'CCC';
        marker.icon = 'people';
        break;
      case 'TheaterEvent':
        marker.color = '0b9fa5';
        marker.icon = 'local_bar';
        break;
      case 'TrainStation':
        marker.color = '999999';
        marker.icon = 'train';
        break;
      default:
        marker.color = '8cbf44';
        marker.icon = 'local_bar';
    }

    return marker;
  }

}