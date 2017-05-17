import { TestBed, inject } from '@angular/core/testing';

import { MapsService } from './mantic.service';

describe('MapsService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [MapsService]
    });
  });

  it('should ...', inject([MapsService], (service: MapsService) => {
    expect(service).toBeTruthy();
  }));
});
