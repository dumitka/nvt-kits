import { TestBed } from '@angular/core/testing';

import { MealOrderService } from './meal-order.service';

describe('MealOrderService', () => {
  let service: MealOrderService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MealOrderService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
