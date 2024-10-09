import { CommonModule } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import { MenuItem, MessageService } from 'primeng/api';
import { SpeedDialModule } from 'primeng/speeddial';
import { ToastModule } from 'primeng/toast';

@Component({
  selector: 'app-linear-button',
  templateUrl: './linear-button.component.html',
  standalone: true,
  imports: [CommonModule, SpeedDialModule, ToastModule],
  providers: [MessageService],
})
export class LinearButtonComponent implements OnInit {
  @Input()
  items: MenuItem[] | undefined;
  @Input()
  direction:
    | 'up'
    | 'down'
    | 'left'
    | 'right'
    | 'up-left'
    | 'up-right'
    | 'down-left'
    | 'down-right'
    | undefined;

  constructor(private messageService: MessageService) {}

  ngOnInit() {}
}
