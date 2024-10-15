import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RootpageComponent } from './rootpage.component';

describe('RootpageComponent', () => {
  let component: RootpageComponent;
  let fixture: ComponentFixture<RootpageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RootpageComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(RootpageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
