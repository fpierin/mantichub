import { ModuleWithProviders } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { MapsComponent } from './maps/maps.component';
import { GeolocationResolver } from './shared/guards/geolocation/geolocation.resolver';

const APP_ROUTES: Routes = [
    { path: '', redirectTo: '/maps', pathMatch: 'full'},
    { path: 'maps', component: MapsComponent,resolve: {geolocation: GeolocationResolver}}
];

export const routing: ModuleWithProviders = RouterModule.forRoot(APP_ROUTES);