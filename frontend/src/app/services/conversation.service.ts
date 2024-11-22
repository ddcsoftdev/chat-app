import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ConversationModel } from '../modules/chatbox/models/conversation-model';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ConversationService {
  private url: string;

  constructor(private http: HttpClient) {
    this.url = environment.apiUrl
   }

   getConversationWithId(id: number): Observable<ConversationModel> {
    return this.http.get<ConversationModel>(`${this.url}/conversation/${id}`);
   }
}
