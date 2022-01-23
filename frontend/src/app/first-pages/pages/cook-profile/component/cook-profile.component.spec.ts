import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CookProfileComponent } from './cook-profile.component';

describe('CookProfileComponent', () => {
  let component: CookProfileComponent;
  let fixture: ComponentFixture<CookProfileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CookProfileComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CookProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
