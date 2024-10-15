import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CarddetailsshowComponent } from './carddetailsshow.component';

describe('CarddetailsshowComponent', () => {
  let component: CarddetailsshowComponent;
  let fixture: ComponentFixture<CarddetailsshowComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CarddetailsshowComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CarddetailsshowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
