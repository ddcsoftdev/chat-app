import { inject, Injectable, PLATFORM_ID } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ConversationModel } from '../../models/conversation.model';
import { catchError, map, Observable, switchMap, throwError } from 'rxjs';
import { UserService } from './user.service';
import { UserModel } from '../../models/user.model';
import { isPlatformBrowser } from '@angular/common';

@Injectable({
  providedIn: 'root',
})
export class ConversationService {
  private readonly baseUrl = environment.conversationUrl;

  private currentUser: UserModel | null = null;
  private platformId = inject(PLATFORM_ID);

  constructor(private http: HttpClient, private userService: UserService) {
    this.userService.currentUser$.subscribe((user) => {
      if (user) {
        this.currentUser = user;
      }
    });
  }

  /**
   * GET All Conversations of a User.
   *
   * It looks automatically for current user or queries API for valid one.
   * @returns
   */
  getAllConversationsOfUser(): Observable<Set<ConversationModel>> {
    const token = isPlatformBrowser(this.platformId)
      ? localStorage.getItem('token')
      : '';
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);

    if (this.currentUser) {
      const url = `${this.baseUrl}/user/${this.currentUser.id}`;
      return this.http.get<ConversationModel[]>(url, { headers }).pipe(
        map((conversations) => new Set(conversations)),
        catchError((error) => {
          console.error('Failed to get Conversations', error);
          return throwError(
            () =>
              new Error(
                `Failed to get conversations for user: ${this.currentUser?.email}`
              )
          );
        })
      );
    }

    //if no current user, get user first then get conversations
    return this.userService.currentUser$.pipe(
      switchMap((user) => {
        if (!user) {
          return throwError(() => new Error('No user found'));
        }

        const url = `${this.baseUrl}/user/${user.id}`;
        return this.http.get<ConversationModel[]>(url, { headers }).pipe(
          map((conversations) => new Set(conversations)),
          catchError((error) => {
            console.error('Failed to get Conversations', error);
            return throwError(
              () =>
                new Error(
                  `Failed to get conversations for user: ${user.getEmail()}`
                )
            );
          })
        );
      })
    );
  }

  /**
   * GET All conversations of a user
   *
   * @param userId userId for API query
   * @returns
   */
  getAllConversationsOfUserParam(
    userId: number
  ): Observable<Set<ConversationModel>> {
    const token = isPlatformBrowser(this.platformId)
      ? localStorage.getItem('token')
      : '';
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    const url = `${this.baseUrl}/user/${userId}`;

    return this.http.get<ConversationModel[]>(url, { headers }).pipe(
      map((conversations) => new Set(conversations)),
      catchError((error) => {
        console.error('Failed to get Conversations', error);
        return throwError(
          () =>
            new Error(
              `Failed to get conversations for user: ${this.currentUser?.getEmail()}`
            )
        );
      })
    );
  }

  /**
   * GET conversation by ID
   * 
   * @param id id for the conversation
   * @returns 
   */
  getConversationById(id: number): Observable<ConversationModel> {
    const token = isPlatformBrowser(this.platformId)
      ? localStorage.getItem('token')
      : '';
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    const url = `${this.baseUrl}/${id}`;
    return this.http.get<ConversationModel>(url, { headers }).pipe(
      map((response) => {
        return new ConversationModel(
          response.id,
          response.users,
          response.messages
        );
      }),
      catchError((error) => {
        console.error('Failed to get conversation:', error);
        return throwError(
          () => new Error(`Failed to get conversation with ID: ${id}`)
        );
      })
    );
  }

  /**
   * POST create new conversation between users
   */
  createNewConversation(userIds: number[]): Observable<void> {
    const token = isPlatformBrowser(this.platformId)
      ? localStorage.getItem('token')
      : '';
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    
    const users = userIds.map(id => ({ id: id }));
    const body = { users: users };
    
    const url = `${this.baseUrl}/create`;
    return this.http.post<void>(url, body, { headers }).pipe(
        catchError((error) => {
            console.error('Failed to create conversation:', error);
            return throwError(
                () => new Error(`Failed to create conversation with users: ${userIds.join(', ')}`)
            );
        })
    );
 }
}

