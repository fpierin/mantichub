import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-maps',
  templateUrl: './maps.component.html',
  styleUrls: ['./maps.component.scss']
})
export class MapsComponent implements OnInit {
  
  public lat: number = 51.678418;
  public lng: number = 7.809007;

  constructor() { }

  ngOnInit() {
  }

}
