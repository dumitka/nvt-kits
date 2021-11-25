import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HallManagerProfileComponent } from './hall-manager-profile.component';

describe('HallManagerProfileComponent', () => {
  let component: HallManagerProfileComponent;
  let fixture: ComponentFixture<HallManagerProfileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HallManagerProfileComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HallManagerProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
