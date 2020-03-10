import { TestBed } from '@angular/core/testing';

import { NaoConformidadeService } from './nao-conformidade.service';

describe('NaoConformidadeService', () => {
  let service: NaoConformidadeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(NaoConformidadeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
