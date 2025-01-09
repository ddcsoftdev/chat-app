import { Injectable, OnDestroy } from '@angular/core';
import { Client } from '@stomp/stompjs';
import { Observable, Subject } from 'rxjs';
import { MessageRegistrationModel } from '../../models/message.model';
import SockJS from 'sockjs-client';

@Injectable({
  providedIn: 'root'
})
export class WebSocketService implements OnDestroy {
  private stompClient!: Client;
  private messageSubject = new Subject<MessageRegistrationModel>();
  private connected = false;

  constructor() {}

  public initConnection(): void {
    const token = localStorage.getItem('token');
    if (!token) {
      console.warn('No token found, skipping WebSocket connection');
      return;
    }
    this.initializeWebSocketConnection();
  }

  private initializeWebSocketConnection(): void {
    this.stompClient = new Client({
      webSocketFactory: () => new SockJS('http://localhost:8080/ws'),
      connectHeaders: {
        Authorization: `Bearer ${localStorage.getItem('token')}`
      },
      onConnect: (frame) => {
        console.log('Connected:', frame);
        this.connected = true;
        this.subscribeToTopic();
      },
      onDisconnect: () => {
        this.connected = false;
      },
      onStompError: (frame) => {
        console.log('STOMP error details:', {
          command: frame.command,
          headers: JSON.stringify(frame.headers),
          body: frame.body
      });
      }
    });

    this.stompClient.activate();
  }

  private subscribeToTopic(): void {
    this.stompClient.subscribe('/topic/public', 
      message => {
          if (message.body) {
              const messageData = JSON.parse(message.body);
              const messageModel = new MessageRegistrationModel(
                  messageData.content,
                  messageData.conversationId,
                  messageData.userId
              );
              this.messageSubject.next(messageModel);
          }
      },
      {
          Authorization: `Bearer ${localStorage.getItem('token')}`
      }
  );
  }

  public sendMessage(message: MessageRegistrationModel): void {
    if (this.connected) {
      this.stompClient.publish({
        destination: '/app/chat.sendMessage',
        body: JSON.stringify({
          content: message.content,
          conversationId: message.conversationId,
          userId: message.userId
        })
      });
    }
  }

  public onMessageReceived(): Observable<MessageRegistrationModel> {
    return this.messageSubject.asObservable();
  }

  ngOnDestroy(): void {
    this.disconnect();
  }

  public disconnect(): void {
    if (this.stompClient) {
      this.stompClient.deactivate();
      this.messageSubject.complete();
    }
  }
}