import { inject, Injectable, PLATFORM_ID } from '@angular/core';
import { BehaviorSubject, catchError, Observable, tap, throwError } from 'rxjs';
import { environment } from '../environments/environment';
import { HttpClient } from '@angular/common/http';
import { AuthLoginResponse } from '../models/auth.model';
import { UserModel } from '../models/user.model';
import { isPlatformBrowser } from '@angular/common';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private readonly loginUrl = environment.loginUrl;
  private platformId = inject(PLATFORM_ID);

  private isAuthenticated = new BehaviorSubject<boolean>(false);
  private currentUser = new BehaviorSubject<UserModel | null>(null);

  isAuthenticated$ = this.isAuthenticated.asObservable();
  currentUser$ = this.currentUser.asObservable();

  constructor(private http: HttpClient) {
      //check if user is already logged in
    if (isPlatformBrowser(this.platformId)) {
      const token = localStorage.getItem('token');
      if (token) {
        this.isAuthenticated.next(true);
        const userData = localStorage.getItem('user');
        if (userData) {
          this.currentUser.next(JSON.parse(userData));
        }
      }
    }
  }

  login(email: string, password: string): Observable<AuthLoginResponse> {
    return this.http.post<AuthLoginResponse>(`${this.loginUrl}`, { email, password })
      .pipe(
        tap(response => {
          //store token and user in localStorage
          if (isPlatformBrowser(this.platformId)) {
            localStorage.setItem('token', response.token);
            localStorage.setItem('user', JSON.stringify(response.chatUserDTO));
          }
          this.isAuthenticated.next(true);
          this.currentUser.next(response.chatUserDTO);
        }),
        catchError(error => {
          console.error('Login failed:', error);
          return throwError(() => new Error(`Login for user ${email} failed.`));
        })
      );
  }

  logout() {
    if (isPlatformBrowser(this.platformId)) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
    }
    this.isAuthenticated.next(false);
    this.currentUser.next(null);
  }

  getToken(): string | null {
    if (isPlatformBrowser(this.platformId)) {
      return localStorage.getItem('token');
    }
    return null;
  }

  getUser(): UserModel | null{
    if (isPlatformBrowser(this.platformId)) {
      const userData = localStorage.getItem('user');
      return userData ? JSON.parse(userData) : null;
    }
    return null;
  }

    isLoggedIn(): boolean {
      return this.isAuthenticated.getValue();
    }
  
    updateUser(user: UserModel): void {
      this.currentUser.next(user);
      if (isPlatformBrowser(this.platformId)) {
        localStorage.setItem('user', JSON.stringify(user));
      }
    }
}