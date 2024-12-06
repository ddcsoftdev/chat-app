import { inject, Injectable, PLATFORM_ID } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class MessageService {
  private readonly baseUrl = environment.apiUrl + '/users';
  private platformId = inject(PLATFORM_ID);
   
  constructor(private http: HttpClient) {

  }
}
