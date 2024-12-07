import { inject, Injectable, PLATFORM_ID } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { UserModel } from '../../models/user.model';
import { ConversationModel } from '../../models/conversation.model';
import { UserService } from './user.service';
import { MessageModel, MessageRegistrationModel, MessageRegistrationModelBuilder } from '../../models/message.model';
import { catchError, map, Observable, throwError } from 'rxjs';
import { isPlatformBrowser } from '@angular/common';

@Injectable({
  providedIn: 'root',
})
export class MessageService {
  private readonly baseUrl = environment.messageUrl;

  private currentUser: UserModel | null = null;
  private currentConversation: ConversationModel | null = null;
  private platformId = inject(PLATFORM_ID);

  constructor(private http: HttpClient, private userService: UserService) {
    this.userService.currentUser$.subscribe((user) => {
      if (user) {
        this.currentUser = user;
      }
    });
  }

  /**
   * POST message in Conversation
   * @param conversationId conversation id
   * @param content message content
   * @returns Observable<MessageModel>
   */
  postMessageByConversationId(
    conversationId: number,
    content: string
): Observable<void> {
    const token = isPlatformBrowser(this.platformId)
        ? localStorage.getItem('token')
        : '';
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);

    if (!this.currentUser) {
        return throwError(() => new Error('No user found'));
    }

    const messageRegistration: MessageRegistrationModel = 
        MessageRegistrationModelBuilder.create()
            .setContent(content)
            .setConversationId(conversationId)
            .setUserId(this.currentUser.id)
            .build();

    const url = `${this.baseUrl}/create`;

    return this.http
        .post<void>(url, {
            content: messageRegistration.content,
            userId: messageRegistration.userId,
            conversationId: messageRegistration.conversationId
        }, { headers })
        .pipe(
            catchError(error => {
                console.error('Failed to post message:', error);
                return throwError(
                    () => new Error(`Failed to post message in conversation: ${conversationId}`)
                );
            })
        );
}
}
