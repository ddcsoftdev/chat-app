import { Component } from '@angular/core';
import { ChatDisplayComponent } from '../chat-display/chat-display.component';
import { ChatTypeboxComponent } from '../chat-typebox/chat-typebox.component';
import { LinearButtonComponent } from '../../../../components/linear-button/linear-button.component';

@Component({
  selector: 'app-chat-main',
  standalone: true,
  imports: [ChatDisplayComponent, ChatTypeboxComponent],
  templateUrl: './chat-main.component.html',
  styleUrl: './chat-main.component.scss'
})
export class ChatMainComponent {

}
