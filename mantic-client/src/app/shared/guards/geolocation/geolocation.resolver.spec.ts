import { TestBed, inject } from '@angular/core/testing';

import { geolocationResolver } from './geolocation.resolver';

describe('GeolocationService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [geolocationResolver]
    });
  });

  it('should ...', inject([geolocationResolver], (service: geolocationResolver) => {
    expect(service).toBeTruthy();
  }));
});
