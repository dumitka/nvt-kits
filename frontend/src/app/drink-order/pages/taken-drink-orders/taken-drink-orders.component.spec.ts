import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TakenDrinkOrdersComponent } from './taken-drink-orders.component';

describe('TakenDrinkOrdersComponent', () => {
  let component: TakenDrinkOrdersComponent;
  let fixture: ComponentFixture<TakenDrinkOrdersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TakenDrinkOrdersComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TakenDrinkOrdersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
