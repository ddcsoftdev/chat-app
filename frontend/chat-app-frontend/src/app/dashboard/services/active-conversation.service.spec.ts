import { TestBed } from '@angular/core/testing';

import { ActiveConversationService } from './active-conversation.service';

describe('ActiveConversationService', () => {
  let service: ActiveConversationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ActiveConversationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
