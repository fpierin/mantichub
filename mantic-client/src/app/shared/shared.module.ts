import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { MapsHeaderComponent } from './maps-header/maps-header.component';

@NgModule({
  declarations: [MapsHeaderComponent],
  imports: [CommonModule],
  exports:[MapsHeaderComponent],
  providers: []
})
export class SharedModule { }
