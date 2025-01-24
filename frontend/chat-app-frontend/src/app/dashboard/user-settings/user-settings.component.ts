import { Component } from '@angular/core';
import { AuthService } from '../../auth/auth.service';

@Component({
  selector: 'app-user-settings',
  standalone: true,
  imports: [],
  templateUrl: './user-settings.component.html',
  styleUrl: './user-settings.component.scss',
})
export class UserSettingsComponent {
  constructor(private authService: AuthService) {}

  handleLogout(): void {
    this.authService.logout();
    window.location.reload();
  }
}
