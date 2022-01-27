import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewMenuReviewComponent } from './new-menu-review.component';

describe('NewMenuReviewComponent', () => {
  let component: NewMenuReviewComponent;
  let fixture: ComponentFixture<NewMenuReviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewMenuReviewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NewMenuReviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
