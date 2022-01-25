import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MealProfileComponent } from './meal-profile.component';

describe('MealProfileComponent', () => {
  let component: MealProfileComponent;
  let fixture: ComponentFixture<MealProfileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MealProfileComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MealProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
