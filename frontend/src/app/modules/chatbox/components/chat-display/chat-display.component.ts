import { Component } from '@angular/core';
import { ScrollPanelModule } from 'primeng/scrollpanel';
import { MessageModel } from '../../models/message-model';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ConversationModel } from '../../models/conversation-model';
import { ConversationService } from '../../../../services/conversation.service';

@Component({
  selector: 'app-chat-display',
  standalone: true,
  imports: [ScrollPanelModule, CommonModule],
  templateUrl: './chat-display.component.html',
  styleUrl: './chat-display.component.scss'
})
export class ChatDisplayComponent {
  conversation: ConversationModel | undefined;
  messages: Set<MessageModel> | undefined;
  
  constructor(private conversationService: ConversationService) {}

  ngOnInit(): void {
    //TODO: make process to select correct id
    const conversationId = 1;

    this.conversationService.getConversationWithId(conversationId).subscribe({
      next: (data) => {
        this.conversation = data;
        this.messages = this.conversation.messages;
      },
      error: (error) => {
        console.error('Error fetching conversation:', error);
      },
    });
  }
}
