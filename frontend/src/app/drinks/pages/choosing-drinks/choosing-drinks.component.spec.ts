import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChoosingDrinksComponent } from './choosing-drinks.component';

describe('ChoosingDrinksComponent', () => {
  let component: ChoosingDrinksComponent;
  let fixture: ComponentFixture<ChoosingDrinksComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ChoosingDrinksComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ChoosingDrinksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
