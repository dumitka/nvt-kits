import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CurrentMenuMealsComponent } from './current-menu-meals.component';

describe('CurrentMenuMealsComponent', () => {
  let component: CurrentMenuMealsComponent;
  let fixture: ComponentFixture<CurrentMenuMealsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CurrentMenuMealsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CurrentMenuMealsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
