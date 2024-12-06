import { inject, Injectable, PLATFORM_ID } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable, catchError, tap, throwError } from 'rxjs';
import { environment } from '../../environments/environment';
import { UserModel } from '../../models/user.model';
import { isPlatformBrowser } from '@angular/common';

@Injectable({
 providedIn: 'root'
})
export class UserService {
 private readonly baseUrl = environment.apiUrl + '/users';
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

 /*
 getCurrentUser(): Observable<UserModel> {
 }

 updateUser(user: UserModel): Observable<UserModel> {
 }
 */
}