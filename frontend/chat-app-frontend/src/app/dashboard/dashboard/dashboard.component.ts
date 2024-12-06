import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';

//delete this for ConversationModel
interface Conversation {
  id: number;
  userName: string;
  lastMessage: string;
  timestamp: string;
  isActive?: boolean;
  unreadCount?: number;
}

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss'
})

export class DashboardComponent {
  conversations: Conversation[] = [
    {
      id: 1,
      userName: 'User 1',
      lastMessage: 'Hey, how are you doing?',
      timestamp: '2m ago',
      isActive: true
    },
    {
      id: 2,
      userName: 'User 2',
      lastMessage: 'The project is looking great!',
      timestamp: '15m ago',
      unreadCount: 3
    },
    {
      id: 3,
      userName: 'User 3',
      lastMessage: 'Can we schedule a meeting?',
      timestamp: '1h ago'
    },
    {
      id: 4,
      userName: 'User 4',
      lastMessage: 'Thanks for your help!',
      timestamp: '2h ago'
    },
    {
      id: 5,
      userName: 'User 5',
      lastMessage: 'See you tomorrow then!',
      timestamp: '1d ago'
    }
  ];
}
