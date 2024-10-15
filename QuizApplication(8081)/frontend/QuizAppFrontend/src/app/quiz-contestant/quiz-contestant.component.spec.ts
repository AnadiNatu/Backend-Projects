import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QuizContestantComponent } from './quiz-contestant.component';

describe('QuizContestantComponent', () => {
  let component: QuizContestantComponent;
  let fixture: ComponentFixture<QuizContestantComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [QuizContestantComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(QuizContestantComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
