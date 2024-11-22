import { bootstrapApplication } from '@angular/platform-browser';
import { appConfig } from './app/app.config';
import { jwtInterceptor } from './app/interceptors/jwt.interceptor';
import { errorInterceptor } from './app/interceptors/error.interceptor';
import { AppComponent } from './app/app.component';
import { provideHttpClient, withInterceptors } from '@angular/common/http';

bootstrapApplication(AppComponent, {
  ...appConfig,
  providers: [
    provideHttpClient(
      withInterceptors([jwtInterceptor, errorInterceptor])
    ),
  ],
}).catch((err) => console.error(err));
