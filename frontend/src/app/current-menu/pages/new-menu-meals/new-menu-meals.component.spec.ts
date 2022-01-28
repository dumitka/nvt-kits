import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewMenuMealsComponent } from './new-menu-meals.component';

describe('NewMenuMealsComponent', () => {
  let component: NewMenuMealsComponent;
  let fixture: ComponentFixture<NewMenuMealsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewMenuMealsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NewMenuMealsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
