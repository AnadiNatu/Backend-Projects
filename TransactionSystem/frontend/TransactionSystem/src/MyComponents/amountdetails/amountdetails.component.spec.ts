import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AmountdetailsComponent } from './amountdetails.component';

describe('AmountdetailsComponent', () => {
  let component: AmountdetailsComponent;
  let fixture: ComponentFixture<AmountdetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AmountdetailsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AmountdetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
