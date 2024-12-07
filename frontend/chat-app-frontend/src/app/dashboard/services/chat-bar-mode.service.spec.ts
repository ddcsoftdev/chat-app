import { TestBed } from '@angular/core/testing';

import { ChatBarModeService } from './chat-bar-mode.service';

describe('ChatBarModeService', () => {
  let service: ChatBarModeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ChatBarModeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
