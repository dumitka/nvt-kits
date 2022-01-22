import { TestBed } from '@angular/core/testing';

import { ChefProfileService } from './chef-profile.service';

describe('ChefProfileService', () => {
  let service: ChefProfileService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ChefProfileService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
