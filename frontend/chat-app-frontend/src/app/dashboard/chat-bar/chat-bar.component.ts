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
import { UserService } from '../services/user.service';
import {
  ChatBarMode,
  ChatBarModeService,
} from '../services/chat-bar-mode.service';
import { take } from 'rxjs';

@Component({
  selector: 'app-chat-bar',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './chat-bar.component.html',
  styleUrl: './chat-bar.component.scss',
})
export class ChatBarComponent {
  conversations: Set<ConversationModel> = new Set();
  users: Set<UserModelNoConversation> = new Set();
  currentMode: ChatBarMode = ChatBarMode.CONVERSATIONS;
  currentUser: UserModel | null = null;
  isLoading: boolean = false;
  errorMessage: string = '';
  platformId = inject(PLATFORM_ID);

  ChatBarMode = ChatBarMode; //expose to html

  constructor(
    private conversationService: ConversationService,
    private activeConversationService: ActiveConversationService,
    private chatUpdateService: ChatUpdateService,
    private userService: UserService,
    private chatBarModeService: ChatBarModeService
  ) {
    if (isPlatformBrowser(this.platformId)) {
      const userData = localStorage.getItem('user');
      if (userData) {
        this.currentUser = JSON.parse(userData);
      }
    }

    this.getAllConversations();
  }

  ngOnInit() {
    //bind the event to refresh on chat update
    this.chatUpdateService.chatUpdated$.subscribe(() => {
      this.getAllConversations();
    });

    //bind chat mode change
    this.chatBarModeService.modeChange$.subscribe((mode: ChatBarMode) => {
      this.currentMode = mode;
      if (mode === ChatBarMode.NEW_CHAT) {
        this.loadUsers();
      }
    });
  }

  getAllConversations(loadLastConversation: boolean = false): void {
    this.isLoading = true;
    this.conversationService.getAllConversationsOfUser().subscribe({
      next: (conversations) => {
        //sort conversations so recent are on top
        const sortedConversations = Array.from(conversations).sort((a, b) => {
          const aMessages = Array.from(a.messages);
          const bMessages = Array.from(b.messages);
          
          if (aMessages.length === 0 && bMessages.length === 0) return 0;
          if (aMessages.length === 0) return 1;
          if (bMessages.length === 0) return -1;

          const aLatestMessage = this.getLatestMessage(aMessages);
          const bLatestMessage = this.getLatestMessage(bMessages);
          
          const dateA = LocalDateTime.parse(aLatestMessage.postedAt.toString());
          const dateB = LocalDateTime.parse(bLatestMessage.postedAt.toString());
          
          return dateB.compareTo(dateA);
        });

        this.conversations = new Set(sortedConversations);

        if (loadLastConversation) {
          if (this.conversations.size > 0) {
            const lastConversation = Array.from(this.conversations.values()).at(0); // Get first (most recent) conversation
            if (lastConversation) {
              this.activeConversationService.setActiveConversation(lastConversation.id);
            }
          }
        } else {
          this.activeConversationService.activeConversationId$
            .pipe(take(1))
            .subscribe((conversationId) => {
              if (conversationId == null && this.conversations.size > 0) {
                this.activeConversationService.setActiveConversation(
                  this.conversations.values().next().value!.id
                );
              }
            });
        }
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

private getLatestMessage(messages: MessageModel[]): MessageModel {
    return messages.sort((a, b) => {
        const dateA = LocalDateTime.parse(a.postedAt.toString());
        const dateB = LocalDateTime.parse(b.postedAt.toString());
        return dateB.compareTo(dateA);
    })[0];
}

  getOtherUsersInConversation(users: Set<UserModelNoConversation>): string {
    if (this.currentUser == null) return '';

    return Array.from(users)
      .filter((user) => user.id !== this.currentUser!.id)
      .map((user) => user.nickname || user.email)
      .join(', ');
  }

  formatLastConversationMessage(
    messages: Set<MessageModel>,
    users: Set<UserModelNoConversation>
  ): string {
    if (!messages || messages.size === 0) return 'No messages yet';
    const lastMessage = this.getLatestMessage(Array.from(messages))
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

  createNewConversation(user: UserModelNoConversation) {
    if (!this.currentUser) {
      console.error('No current user found');
      return;
    }

    const existingConversation = Array.from(this.conversations).find((conv) => {
      const conversationUsers = Array.from(conv.users);
      return (
        conversationUsers.length === 2 &&
        conversationUsers.some((u) => u.id === this.currentUser!.id) &&
        conversationUsers.some((u) => u.id === user.id)
      );
    });

    if (existingConversation) {
      this.activeConversationService.setActiveConversation(
        existingConversation.id
      );
      this.chatBarModeService.changeMode(ChatBarMode.CONVERSATIONS);
      return;
    }

    const userIds = [this.currentUser.id, user.id];

    this.conversationService.createNewConversation(userIds).subscribe({
      next: () => {
        this.chatBarModeService.changeMode(ChatBarMode.CONVERSATIONS);
        this.getAllConversations(true);
      },
      error: (error) => {
        console.error('Error creating conversation:', error);
        this.errorMessage = 'Failed to create conversation';
      },
    });
  }

  loadUsers() {
    this.isLoading = true;
    this.userService.getAllUsersNoConversationModel().subscribe({
      next: (users) => {
        if (this.currentUser) {
          const sortedUsers = Array.from(users)
           .filter(user => user.id !== this.currentUser!.id)
           .sort((a, b) => a.nickname.localeCompare(b.nickname));
           
         this.users = new Set(sortedUsers);
        } else {
          this.users = users;
        }
        this.isLoading = false;
      },
      error: (error) => {
        console.error('Error loading users:', error);
        this.errorMessage = 'Failed to load users';
        this.isLoading = false;
      },
    });
  }
}
