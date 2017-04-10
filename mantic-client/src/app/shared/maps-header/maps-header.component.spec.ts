import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MapsHeaderComponent } from './maps-header.component';

describe('MapsHeaderComponent', () => {
  let component: MapsHeaderComponent;
  let fixture: ComponentFixture<MapsHeaderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MapsHeaderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MapsHeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
