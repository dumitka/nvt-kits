import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MealsOfCategoryComponent } from './meals-of-category.component';

describe('MealsOfCategoryComponent', () => {
  let component: MealsOfCategoryComponent;
  let fixture: ComponentFixture<MealsOfCategoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MealsOfCategoryComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MealsOfCategoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
