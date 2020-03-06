import { TestBed } from '@angular/core/testing';

import { RiscoAcidenteService } from './risco-acidente.service';

describe('RiscoAcidenteService', () => {
  let service: RiscoAcidenteService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RiscoAcidenteService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
