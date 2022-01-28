import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChargeOrderDialogComponent } from './charge-order-dialog.component';

describe('ChargeOrderDialogComponent', () => {
  let component: ChargeOrderDialogComponent;
  let fixture: ComponentFixture<ChargeOrderDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ChargeOrderDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ChargeOrderDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
