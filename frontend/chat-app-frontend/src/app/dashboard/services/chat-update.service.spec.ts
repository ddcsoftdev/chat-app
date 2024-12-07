import { TestBed } from '@angular/core/testing';

import { ChatUpdateService } from './chat-update.service';

describe('ChatUpdateService', () => {
  let service: ChatUpdateService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ChatUpdateService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
