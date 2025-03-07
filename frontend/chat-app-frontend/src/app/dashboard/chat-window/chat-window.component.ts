import {
  Component,
  ElementRef,
  inject,
  PLATFORM_ID,
  ViewChild,
  AfterViewInit,
  OnInit,
  OnDestroy,
} from '@angular/core';
import { ConversationService } from '../services/conversation.service';
import { ActiveConversationService } from '../services/active-conversation.service';
import { ConversationModel } from '../../models/conversation.model';
import { CommonModule, isPlatformBrowser } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { LocalDateTime } from '../../models/date.model';
import { UserModelNoConversation } from '../../models/user.model';
import { MessageModel } from '../../models/message.model';
import { MessageService } from '../services/message.service';
import { ChatUpdateService } from '../services/chat-update.service';
import { WebSocketService } from '../services/websocket.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-chat-window',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './chat-window.component.html',
  styleUrl: './chat-window.component.scss',
})
export class ChatWindowComponent implements OnInit, AfterViewInit, OnDestroy {
  @ViewChild('scrollContainer') private scrollContainer!: ElementRef;

  currentConversation: ConversationModel | null = null;
  isLoading = false;
  errorMessage = '';
  currentUserId: number | null = null;
  newMessage = '';
  private platformId = inject(PLATFORM_ID);
  private subscriptions = new Subscription();

  constructor(
    private activeConversationService: ActiveConversationService,
    private conversationService: ConversationService,
    private messageService: MessageService,
    private chatUpdateService: ChatUpdateService,
    private webSocketService: WebSocketService
  ) {
    if (isPlatformBrowser(this.platformId)) {
      const userData = localStorage.getItem('user');
      if (userData) {
        this.currentUserId = JSON.parse(userData).id;
        this.webSocketService.initConnection();
      }
    }
  }

  ngOnInit() {
    this.subscriptions.add(
      this.activeConversationService.activeConversationId$.subscribe((id) => {
        if (id) this.loadConversation(id);
      })
    );

    this.subscriptions.add(
      this.webSocketService.onMessageReceived().subscribe((message) => {
        if (this.currentConversation?.id === message.conversationId) {
          this.loadConversation(this.currentConversation.id);
        }
      })
    );

    this.subscriptions.add(
      this.activeConversationService.activeConversationId$.subscribe((id) => {
        if (id) this.loadConversation(id);
      })
    );
  }

  ngAfterViewInit() {
    this.scrollToBottom();
  }

  ngOnDestroy() {
    this.subscriptions.unsubscribe();
  }

  private loadConversation(id: number) {
    this.isLoading = true;
    this.errorMessage = '';

    this.subscriptions.add(
      this.conversationService.getConversationById(id).subscribe({
        next: (conversation) => {
          this.currentConversation = conversation;
          this.isLoading = false;
          this.scrollToBottom();
        },
        error: (error) => {
          console.error('Error loading conversation:', error);
          this.errorMessage = 'Failed to load conversation';
          this.isLoading = false;
        },
      })
    );
  }

  getUserInitial(userId: number): string {
    if (!this.currentConversation?.users) return 'U';
    const user = Array.from(this.currentConversation.users).find(
      (u: UserModelNoConversation) => u.id === userId
    );
    return user?.nickname?.charAt(0).toUpperCase() || 'U';
  }

  formatMessageTime(dateTime: LocalDateTime): string {
    return LocalDateTime.parse(dateTime.toString()).getTimeHHMM().toString();
  }

  getConversationTitle(): string {
    if (!this.currentConversation?.users) return 'Select a conversation';
    return (
      Array.from(this.currentConversation.users)
        .filter((user) => user.id !== this.currentUserId)
        .map((user) => user.nickname)
        .join(', ') || 'Chat'
    );
  }

  sendMessage() {
    if (!this.newMessage.trim() || !this.currentConversation) return;

    this.subscriptions.add(
      this.messageService
        .postMessageByConversationId(
          this.currentConversation.id,
          this.newMessage.trim()
        )
        .subscribe({
          next: () => {
            this.newMessage = '';
            this.scrollToBottom();
            this.loadConversation(this.currentConversation!.id);
            this.chatUpdateService.notifyNewMessage();
          },
          error: (error) => {
            console.error('Error sending message:', error);
            this.errorMessage = 'Failed to send message';
          },
        })
    );
  }

  private scrollToBottom(): void {
    setTimeout(() => {
      if (this.scrollContainer?.nativeElement) {
        this.scrollContainer.nativeElement.scrollTop =
          this.scrollContainer.nativeElement.scrollHeight;
      }
    });
  }

  getMessagesArray(): Array<MessageModel> {
    if (!this.currentConversation || !this.currentConversation.messages) {
      return new Array<MessageModel>();
    }

    return Array.from<MessageModel>(this.currentConversation.messages).sort(
      (a, b) => {
        const dateA = LocalDateTime.parse(a.postedAt.toString());
        const dateB = LocalDateTime.parse(b.postedAt.toString());
        return dateA.compareTo(dateB);
      }
    );
  }

  hasMessages(): boolean {
    if (!this.currentConversation || !this.currentConversation.messages) {
      return false;
    }
    return (
      Array.from<MessageModel>(this.currentConversation.messages).length > 0
    );
  }
}
