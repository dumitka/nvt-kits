import { TestBed } from '@angular/core/testing';

import { MealsOfCategoryService } from './meals-of-category.service';

describe('MealsOfCategoryService', () => {
  let service: MealsOfCategoryService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MealsOfCategoryService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
