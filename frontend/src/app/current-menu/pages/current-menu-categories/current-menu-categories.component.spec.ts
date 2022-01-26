import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CurrentMenuCategoriesComponent } from './current-menu-categories.component';

describe('CurrentMenuCategoriesComponent', () => {
  let component: CurrentMenuCategoriesComponent;
  let fixture: ComponentFixture<CurrentMenuCategoriesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CurrentMenuCategoriesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CurrentMenuCategoriesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
