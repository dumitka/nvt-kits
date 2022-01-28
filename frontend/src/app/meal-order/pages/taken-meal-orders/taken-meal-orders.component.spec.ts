import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TakenMealOrdersComponent } from './taken-meal-orders.component';

describe('TakenMealOrdersComponent', () => {
  let component: TakenMealOrdersComponent;
  let fixture: ComponentFixture<TakenMealOrdersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TakenMealOrdersComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TakenMealOrdersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
