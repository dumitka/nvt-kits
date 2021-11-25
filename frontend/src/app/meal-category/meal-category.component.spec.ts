import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MealCategoryComponent } from './meal-category.component';

describe('MealCategoryComponent', () => {
  let component: MealCategoryComponent;
  let fixture: ComponentFixture<MealCategoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MealCategoryComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MealCategoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
