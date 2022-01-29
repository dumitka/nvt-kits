import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewDrinkOrdersComponent } from './new-drink-orders.component';

describe('NewDrinkOrdersComponent', () => {
  let component: NewDrinkOrdersComponent;
  let fixture: ComponentFixture<NewDrinkOrdersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewDrinkOrdersComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NewDrinkOrdersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
