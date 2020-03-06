import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RiscoAcidenteComponent } from './risco-acidente.component';

describe('RiscoAcidenteComponent', () => {
  let component: RiscoAcidenteComponent;
  let fixture: ComponentFixture<RiscoAcidenteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RiscoAcidenteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RiscoAcidenteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
