import { TestBed } from '@angular/core/testing';

import { CookProfileService } from './cook-profile.service';

describe('CookProfileService', () => {
  let service: CookProfileService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CookProfileService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
