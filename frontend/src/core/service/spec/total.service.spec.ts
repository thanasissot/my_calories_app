import { TestBed } from '@angular/core/testing';

import { MealService } from '../meal.service';

describe('TotalService', () => {
  let service: MealService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MealService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
