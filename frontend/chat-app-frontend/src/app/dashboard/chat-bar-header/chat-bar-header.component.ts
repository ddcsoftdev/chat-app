import { Component, EventEmitter, inject, Output, PLATFORM_ID } from '@angular/core';
import { UserService } from '../services/user.service';
import { UserModel, UserModelNoConversation } from '../../models/user.model';
import { ChatBarMode, ChatBarModeService } from '../services/chat-bar-mode.service';
import { CommonModule, isPlatformBrowser } from '@angular/common';
import { UserSettingsComponent } from "../user-settings/user-settings.component";

@Component({
  selector: 'app-chat-bar-header',
  standalone: true,
  imports: [CommonModule, UserSettingsComponent],
  templateUrl: './chat-bar-header.component.html',
  styleUrl: './chat-bar-header.component.scss'
})
export class ChatBarHeaderComponent {
  currentMode: ChatBarMode = ChatBarMode.CONVERSATIONS;
  currentUser: UserModel | null = null;
  isSettingsOpen: boolean = false;
  platformId = inject(PLATFORM_ID);
  
  ChatBarMode = ChatBarMode; //expose to html

    constructor(private chatBarModeService: ChatBarModeService) {
        this.chatBarModeService.modeChange$.subscribe(mode => {
            this.currentMode = mode;
        });

        if (isPlatformBrowser(this.platformId)) {
          const userData = localStorage.getItem('user');
          if (userData) {
            this.currentUser = JSON.parse(userData);
          }
        }
    }

    toggleMode() {
      const newMode = this.currentMode === ChatBarMode.CONVERSATIONS 
          ? ChatBarMode.NEW_CHAT 
          : ChatBarMode.CONVERSATIONS;
      this.chatBarModeService.changeMode(newMode);
  }

  toggleSettings() {
    this.isSettingsOpen = !this.isSettingsOpen;
  }
}
