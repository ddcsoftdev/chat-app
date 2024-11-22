import { HttpInterceptorFn } from "@angular/common/http";
import {jwtDecode} from 'jwt-decode';

export const jwtInterceptor: HttpInterceptorFn = (req, next) => {
    const token = localStorage.getItem('jwtToken');
  
    if (token && !isTokenExpired(token)) {
      req = req.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`,
        },
      });
    } else {
      console.warn('JWT is missing or expired. Redirecting to login.');
    }
  
    return next(req);
  };
  

  export function isTokenExpired(token: string): boolean {
    try {
      const { exp } = jwtDecode<{ exp: number }>(token);
      return Date.now() >= exp * 1000;
    } catch (error) {
      console.error('Invalid JWT:', error);
      return true;
    }
  }

