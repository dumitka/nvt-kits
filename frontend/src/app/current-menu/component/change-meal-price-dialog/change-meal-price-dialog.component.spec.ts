import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChangeMealPriceDialogComponent } from './change-meal-price-dialog.component';

describe('ChangeMealPriceDialogComponent', () => {
  let component: ChangeMealPriceDialogComponent;
  let fixture: ComponentFixture<ChangeMealPriceDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ChangeMealPriceDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ChangeMealPriceDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
