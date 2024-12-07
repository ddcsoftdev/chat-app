import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

export enum ChatBarMode {
  CONVERSATIONS = 'conversations',
  NEW_CHAT = 'new_chat'
}

@Injectable({
  providedIn: 'root'
})
export class ChatBarModeService {
  private modeChange = new Subject<ChatBarMode>();
  modeChange$ = this.modeChange.asObservable();

  changeMode(mode: ChatBarMode) {
      this.modeChange.next(mode);
  }
}
