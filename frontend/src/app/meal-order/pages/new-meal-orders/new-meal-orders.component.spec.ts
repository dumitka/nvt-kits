import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewMealOrdersComponent } from './new-meal-orders.component';

describe('NewMealOrdersComponent', () => {
  let component: NewMealOrdersComponent;
  let fixture: ComponentFixture<NewMealOrdersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewMealOrdersComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NewMealOrdersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
