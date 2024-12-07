import { Component, ElementRef, inject, PLATFORM_ID, ViewChild, AfterViewInit } from '@angular/core';
import { ConversationService } from '../services/conversation.service';
import { ActiveConversationService } from '../services/active-conversation.service';
import { ConversationModel } from '../../models/conversation.model';
import { CommonModule, isPlatformBrowser } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { LocalDateTime } from '../../models/date.model';
import { UserModel, UserModelNoConversation } from '../../models/user.model';
import { MessageModel } from '../../models/message.model';
import { MessageService } from '../services/message.service';

@Component({
  selector: 'app-chat-window',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './chat-window.component.html',
  styleUrl: './chat-window.component.scss'
})
export class ChatWindowComponent implements AfterViewInit {
  @ViewChild('scrollContainer') private scrollContainer!: ElementRef;
 
  currentConversation: ConversationModel | null = null;
  isLoading: boolean = false;
  errorMessage: string = '';
  currentUserId: number | null = null;
  newMessage: string = '';
  private platformId = inject(PLATFORM_ID);

  constructor(
    private activeConversationService: ActiveConversationService,
    private conversationService: ConversationService,
    private messageService: MessageService
  ) {
    if (isPlatformBrowser(this.platformId)) {
      const userData = localStorage.getItem('user');
      if (userData) {
        this.currentUserId = JSON.parse(userData).id;
      }
    }
  }

  ngOnInit() {
    this.activeConversationService.activeConversationId$.subscribe(id => {
      if (id) {
        this.loadConversation(id);
      }
    });
  }

  ngAfterViewInit() {
    this.scrollToBottom();
  }

  private loadConversation(id: number) {
    this.isLoading = true;
    this.errorMessage = '';
    
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
      }
    });
  }

  getUserInitial(userId: number): string {
    if (!this.currentConversation || !this.currentConversation.users) {
      return 'U';
    }
    const users: Array<UserModelNoConversation> = Array.from(this.currentConversation.users);
    const user: UserModelNoConversation | undefined = users.find(
      (u: UserModelNoConversation): boolean => u.id === userId
    );
    return user?.nickname?.charAt(0).toUpperCase() || 'U';
  }
 
  formatMessageTime(dateTime: LocalDateTime): string {
    const postedAt = LocalDateTime.parse(dateTime.toString());
    return postedAt.getTimeHHMM().toString();
  }
 
  getConversationTitle(): string {
    if (!this.currentConversation || !this.currentConversation.users) {
      return 'Select a conversation';
    }
    const otherUsers = Array.from(this.currentConversation.users)
      .filter(user => user.id !== this.currentUserId)
      .map(user => user.nickname)
      .join(', ');
    return otherUsers || 'Chat';
  }
 
  sendMessage() {
    if (!this.newMessage.trim() || !this.currentConversation) return;
   
   this.messageService.postMessageByConversationId(
       this.currentConversation.id,
       this.newMessage.trim()
   ).subscribe({
       next: () => {
           this.newMessage = '';
           this.scrollToBottom();
           this.loadConversation(this.currentConversation!.id);
       },
       error: (error) => {
           console.error('Error sending message:', error);
           this.errorMessage = 'Failed to send message';
       }
   });
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
    
    return Array.from<MessageModel>(this.currentConversation.messages)
        .sort((a, b) => {
          const dateA = LocalDateTime.parse(a.postedAt.toString());
          const dateB = LocalDateTime.parse(b.postedAt.toString());
          return dateA.compareTo(dateB);
        });
}

  hasMessages(): boolean {
    if (!this.currentConversation || !this.currentConversation.messages) {
      return false;
    }
    return Array.from<MessageModel>(this.currentConversation.messages).length > 0;
  }
}