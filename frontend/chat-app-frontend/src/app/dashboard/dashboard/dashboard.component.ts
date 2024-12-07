import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { ChatBarComponent } from "../chat-bar/chat-bar.component";
import { ChatBarHeaderComponent } from "../chat-bar-header/chat-bar-header.component";
import { ChatWindowComponent } from "../chat-window/chat-window.component";

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, ChatBarComponent, ChatBarHeaderComponent, ChatWindowComponent],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss'
})

export class DashboardComponent {
 
}
