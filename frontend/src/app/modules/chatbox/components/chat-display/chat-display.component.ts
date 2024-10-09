import { Component } from '@angular/core';
import { ScrollPanelModule } from 'primeng/scrollpanel';
import { ChatMessageModel } from '../../models/chat-message-model';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-chat-display',
  standalone: true,
  imports: [ScrollPanelModule, CommonModule],
  templateUrl: './chat-display.component.html',
  styleUrl: './chat-display.component.scss'
})
export class ChatDisplayComponent {
  owner: string = 'John';
  conversation:  ChatMessageModel[] =  [
    { id: '1', owner: 'John', message: 'Hey, how are you?' },
    { id: '2', owner: 'Jane', message: 'I am good, thanks! How about you?' },
    { id: '3', owner: 'John', message: 'Doing well, just getting some work done.' },
    { id: '4', owner: 'Jane', message: 'That’s great! What are you working on?' },
    { id: '5', owner: 'John', message: 'Just a small project. What about you?' },
    { id: '6', owner: 'Jane', message: 'Catching up on some reading. Have you checked the new article on Angular?' },
    { id: '7', owner: 'John', message: 'Not yet! Sounds interesting, I’ll take a look later.' }
  ];
}
