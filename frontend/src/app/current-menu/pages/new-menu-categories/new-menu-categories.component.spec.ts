import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewMenuCategoriesComponent } from './new-menu-categories.component';

describe('NewMenuCategoriesComponent', () => {
  let component: NewMenuCategoriesComponent;
  let fixture: ComponentFixture<NewMenuCategoriesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewMenuCategoriesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NewMenuCategoriesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
