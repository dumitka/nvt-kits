import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CurrentMenuMealProfileComponent } from './current-menu-meal-profile.component';

describe('CurrentMenuMealProfileComponent', () => {
  let component: CurrentMenuMealProfileComponent;
  let fixture: ComponentFixture<CurrentMenuMealProfileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CurrentMenuMealProfileComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CurrentMenuMealProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
