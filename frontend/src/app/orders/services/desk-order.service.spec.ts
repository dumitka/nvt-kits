import { TestBed } from '@angular/core/testing';

import { DeskOrderService } from './desk-order.service';

describe('DeskOrderService', () => {
  let service: DeskOrderService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DeskOrderService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
