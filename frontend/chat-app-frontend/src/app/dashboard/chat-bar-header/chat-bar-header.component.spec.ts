import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChatBarHeaderComponent } from './chat-bar-header.component';

describe('ChatBarHeaderComponent', () => {
  let component: ChatBarHeaderComponent;
  let fixture: ComponentFixture<ChatBarHeaderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ChatBarHeaderComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ChatBarHeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
