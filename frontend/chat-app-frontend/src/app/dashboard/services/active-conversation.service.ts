import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ActiveConversationService {
  
  private activeConversationId = new BehaviorSubject<number | null>(null);
  activeConversationId$ = this.activeConversationId.asObservable();

  setActiveConversation(id: number) {
    this.activeConversationId.next(id);
  }
}
