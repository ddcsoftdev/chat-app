import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ChatUpdateService {
  private chatUpdated = new Subject<void>();
  chatUpdated$ = this.chatUpdated.asObservable();

  notifyNewMessage() {
    this.chatUpdated.next();
  }
}
