import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SigupClientComponent } from './sigup-client.component';

describe('SigupClientComponent', () => {
  let component: SigupClientComponent;
  let fixture: ComponentFixture<SigupClientComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SigupClientComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SigupClientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
