import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChangeMealComponent } from './change-meal.component';

describe('ChangeMealComponent', () => {
  let component: ChangeMealComponent;
  let fixture: ComponentFixture<ChangeMealComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ChangeMealComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ChangeMealComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
