import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChatTypeboxComponent } from './chat-typebox.component';

describe('ChatTypeboxComponent', () => {
  let component: ChatTypeboxComponent;
  let fixture: ComponentFixture<ChatTypeboxComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ChatTypeboxComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ChatTypeboxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
