import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllDrinksComponent } from './all-drinks.component';

describe('AllDrinksComponent', () => {
  let component: AllDrinksComponent;
  let fixture: ComponentFixture<AllDrinksComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AllDrinksComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AllDrinksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
