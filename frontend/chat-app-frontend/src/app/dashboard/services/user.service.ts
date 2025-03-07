import { inject, Injectable, PLATFORM_ID } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject, Observable, catchError, map, tap, throwError } from 'rxjs';
import { environment } from '../../../environments/environment';
import { UserModel, UserModelNoConversation } from '../../models/user.model';
import { isPlatformBrowser } from '@angular/common';

@Injectable({
 providedIn: 'root'
})
export class UserService {
 private readonly baseUrl = environment.userUrl;
 private platformId = inject(PLATFORM_ID);
 
 private currentUser = new BehaviorSubject<UserModel | null>(null);
 currentUser$ = this.currentUser.asObservable();

 constructor(private http: HttpClient) {
   if (isPlatformBrowser(this.platformId)) {
     const userData = localStorage.getItem('user');
     if (userData) {
       this.currentUser.next(JSON.parse(userData));
     }
   }
 }

 getAllUsersNoConversationModel(): Observable<Set<UserModelNoConversation>> {
  const token = isPlatformBrowser(this.platformId) ? localStorage.getItem('token') : '';
  const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);

  return this.http.get<UserModelNoConversation[]>(`${this.baseUrl}`, { headers }).pipe(
      map(users => new Set(users)),
      catchError(error => {
          console.error('Failed to get users:', error);
          return throwError(() => new Error('Failed to fetch users'));
      })
  );
}
 /*
 getCurrentUser(): Observable<UserModel> {
 }

 updateUser(user: UserModel): Observable<UserModel> {
 }
 */
}