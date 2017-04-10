import { ModuleWithProviders } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { MapsComponent } from './maps/maps.component';

const APP_ROUTES: Routes = [
    { path: 'maps', component: MapsComponent },
    { path: '', component: MapsComponent }
];

export const routing: ModuleWithProviders = RouterModule.forRoot(APP_ROUTES);