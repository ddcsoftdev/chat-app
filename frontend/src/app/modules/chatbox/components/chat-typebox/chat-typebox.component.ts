import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { InputTextareaModule } from 'primeng/inputtextarea';
import { SpeedDialModule } from 'primeng/speeddial';
import { LinearButtonComponent } from '../../../../components/linear-button/linear-button.component';
import { MenuItem } from 'primeng/api';
@Component({
  selector: 'app-chat-typebox',
  standalone: true,
  imports: [
    InputTextareaModule,
    ReactiveFormsModule,
    SpeedDialModule,
    LinearButtonComponent,
  ],
  templateUrl: './chat-typebox.component.html',
  styleUrl: './chat-typebox.component.scss',
})
export class ChatTypeboxComponent implements OnInit {
  formGroup!: FormGroup;
  linearButtonItems: MenuItem[] | undefined;
  linearButtonDir:
    | 'up'
    | 'down'
    | 'left'
    | 'right'
    | 'up-left'
    | 'up-right'
    | 'down-left'
    | 'down-right'
    | undefined;

  ngOnInit() {
    this.formGroup = new FormGroup({
      text: new FormControl(''),
    });

    this.linearButtonDir = 'right';
    this.linearButtonItems = [
      {
        label: 'Update',
        icon: 'pi pi-refresh',
        command: () => {
          console.log('Update action');
        },
      },
      {
        label: 'Delete',
        icon: 'pi pi-times',
        command: () => {
          console.log('Delete action');
        },
      },
      {
        label: 'Upload',
        icon: 'pi pi-upload',
        command: () => {
          console.log('Upload action');
        },
      },
      {
        label: 'Angular',
        icon: 'pi pi-external-link',
        command: () => {
          console.log('Navigating to Angular website');
          window.open('https://angular.io', '_blank');
        },
      },
    ];
  }
}
