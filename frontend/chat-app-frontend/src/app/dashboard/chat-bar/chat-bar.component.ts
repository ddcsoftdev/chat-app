import { CommonModule, isPlatformBrowser } from '@angular/common';
import { Component, inject, PLATFORM_ID } from '@angular/core';
import { ConversationModel } from '../../models/conversation.model';
import { ConversationService } from '../services/conversation.service';
import { UserModel, UserModelNoConversation } from '../../models/user.model';
import { MessageModel } from '../../models/message.model';
import { LocalDateTime } from '../../models/date.model';
import { ActiveConversationService } from '../services/active-conversation.service';
import { send } from 'process';
import { ChatUpdateService } from '../services/chat-update.service';

@Component({
  selector: 'app-chat-bar',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './chat-bar.component.html',
  styleUrl: './chat-bar.component.scss',
})
export class ChatBarComponent {
  conversations: Set<ConversationModel> = new Set();
  currentUser: UserModel | null = null;
  isLoading: boolean = false;
  errorMessage: string = '';
  platformId = inject(PLATFORM_ID);

  constructor(
    private conversationService: ConversationService,
    private activeConversationService: ActiveConversationService,
    private chatUpdateService: ChatUpdateService
  ) {
    if (isPlatformBrowser(this.platformId)) {
      const userData = localStorage.getItem('user');
      if (userData) {
        this.currentUser = JSON.parse(userData);
      }
    }
    this.getAllConversations();
    this.chatUpdateService.chatUpdated$.subscribe(() => {
      this.getAllConversations();
  });
  }

  getAllConversations(): void {
    this.isLoading = true;
    this.conversationService.getAllConversationsOfUser().subscribe({
      next: (conversations) => {
        this.conversations = conversations;
      },
      error: (error) => {
        console.error('Failed to get conversations:', error);
        this.errorMessage = error.message || 'Failed to load conversations';
      },
      complete: () => {
        this.isLoading = false;
      },
    });
  }

  getOtherUsersInConversation(users: Set<UserModelNoConversation>): string {
    if (this.currentUser == null) return '';

    return Array.from(users)
      .filter((user) => user.id !== this.currentUser!.id)
      .map((user) => user.nickname || user.email)
      .join(', ');
  }

  getConversationLastMessage(
    messages: Set<MessageModel>,
    users: Set<UserModelNoConversation>
  ): string {
    if (!messages || messages.size === 0) return 'No messages yet';

    const sortedMessages = Array.from(messages).sort((a, b) => {
      const dateA = LocalDateTime.parse(a.postedAt.toString());
      const dateB = LocalDateTime.parse(b.postedAt.toString());
      return dateB.compareTo(dateA);
    });

    const lastMessage = sortedMessages[0];
    if (!lastMessage) return 'No messages yet';

    const sender = Array.from(users).find(
      (user) => user.id === lastMessage.userId
    );
    let senderNickname = sender?.nickname || 'Unknown User';
    if (sender && this.currentUser && sender.id == this.currentUser.id) {
      senderNickname = 'You';
    }

    return `${senderNickname}: ${lastMessage.content}`;
  }

  onConversationClick(conversationId: number) {
    this.activeConversationService.setActiveConversation(conversationId);
  }
}
