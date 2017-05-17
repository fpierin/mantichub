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

  /* FILTER FORM */

  onFilterSubmit(form){

    this.dataSend = { 
      "type": form.value.eventType || 'Event',
      "latitude": this.position.lat,
      "longitude": this.position.lng
     }

     this.getMarkers(this.dataSend);
     this.hasIdle = true;
  }

  getMarkers(dataSend){
    this.hasLoad = true;
    this.mapsService.getMarkers(dataSend)
      .then( (res:any) => {
        this.activedContent = 'result';
        this.markers = res.resources;
        this.query = res.sparqlQuery;
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
    if(typeof this.position !== 'undefined' && this.hasIdle ){
      this.dataSend.latitude = this.position.lat;
      this.dataSend.longitude = this.position.lng;
      this.getMarkers(this.dataSend);
    }
  }

  backTo(content:string){
    if(content){
      this.activedContent = content;
      return;
    }
  }

  onQuerySubmit(form){
      this.mapsService.getQuery({"query": form.value.query})
      .then( (res:any) => {
        console.log(res);
      })
      .catch( (err:any) =>  console.log(err))
  }

}

//PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#> PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#> PREFIX iweb:<http://integraweb.ddns.net/> PREFIX schema:<http://schema.org/> SELECT DISTINCT ?title ?latitude ?longitude ?startDate ?endDate ?startTime ?endTime ?cuisine ?description ?priceRange ?telephone ?overview ?streetAddress ?price ?type ?url ?image  WHERE { 	?s schema:title ?titleObj ; 	schema:latitude ?latitudeObj ; 	schema:longitude ?longitudeObj ; 	iweb:near ?nearObj;  	rdf:type rdfs:Resource ; 	rdf:type ?typeObj . 	?typeObj rdfs:subClassOf ?subClassObj . 	?nearObj rdf:type schema:TrainStation . 	OPTIONAL { ?s schema:cuisine ?cuisineObj } 	OPTIONAL { ?s schema:description ?descriptionObj } 	OPTIONAL { ?s schema:endDate ?endDateObj } 	OPTIONAL { ?s schema:endTime ?endTimeObj }	 	OPTIONAL { ?s schema:overview ?overviewObj } 	OPTIONAL { ?s schema:price ?priceObj }	 	OPTIONAL { ?s schema:priceRange ?priceRangeObj } 	OPTIONAL { ?s schema:startDate ?startDateObj } 	OPTIONAL { ?s schema:startTime ?startTimeObj } 	OPTIONAL { ?s schema:streetAddress ?streetAddressObj }	 	OPTIONAL { ?s schema:telephone ?telephoneObj } 	OPTIONAL { ?s schema:serviceURL ?urlObj } 	OPTIONAL { ?s schema:image ?imageObj } 	values ?subClassObj { schema:Event schema:FoodEstablishment schema:CivicStructure }  	BIND (str(?titleObj) as ?title) 	BIND (str(?latitudeObj) as ?latitude) 	BIND (str(?longitudeObj) as ?longitude) 	BIND (str(?cuisineObj) as ?cuisine) 	BIND (str(?descriptionObj) as ?description) 	BIND (str(?endDateObj) as ?endDate) 	BIND (str(?overviewObj) as ?overview) 	BIND (str(?priceObj) as ?price) 	BIND (str(?priceRangeObj) as ?priceRange) 	BIND (str(?startDateObj) as ?startDate) 	BIND (str(?streetAddressObj) as ?streetAddress) 	BIND (str(?telephoneObj) as ?telephone) 	BIND (str(?urlObj) as ?url) 	BIND (str(?imageObj) as ?image) 	BIND ( strafter(strafter( str(?typeObj), "http://" ),"/") as ?type ) 	BIND ( strafter( str(?endTimeObj), "T" ) as ?endTime ) 	BIND ( strafter( str(?startTimeObj), "T" ) as ?startTime ) 	FILTER NOT EXISTS { 		?s rdf:type ?subtype . 		?subtype rdfs:subClassOf* ?typeObj . 		filter ( ?subtype != ?typeObj ) 	}  } LIMIT 10000