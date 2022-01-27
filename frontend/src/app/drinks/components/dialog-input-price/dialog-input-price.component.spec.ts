import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogInputPriceComponent } from './dialog-input-price.component';

describe('DialogInputPriceComponent', () => {
  let component: DialogInputPriceComponent;
  let fixture: ComponentFixture<DialogInputPriceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DialogInputPriceComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogInputPriceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
