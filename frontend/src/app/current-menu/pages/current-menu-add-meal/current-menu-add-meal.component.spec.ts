import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CurrentMenuAddMealComponent } from './current-menu-add-meal.component';

describe('CurrentMenuAddMealComponent', () => {
  let component: CurrentMenuAddMealComponent;
  let fixture: ComponentFixture<CurrentMenuAddMealComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CurrentMenuAddMealComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CurrentMenuAddMealComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
