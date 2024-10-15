import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SigupCompanyComponent } from './sigup-company.component';

describe('SigupCompanyComponent', () => {
  let component: SigupCompanyComponent;
  let fixture: ComponentFixture<SigupCompanyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SigupCompanyComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SigupCompanyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
