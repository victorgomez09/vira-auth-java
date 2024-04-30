import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { UserService } from '../services/user.service';
import { userStore } from '../shared/stores/user.store';

export const authGuard: CanActivateFn = (_route, _state) => {
  try {
    // Guard dependency inections
    const router = inject(Router);
    const userService = inject(UserService);

    const token = localStorage.getItem('access_token');
    if (!token) {
      return router.navigate(['/login']);
    }

    userService.getCurrentUser();
    console.log('userStorage', userStore());

    return true;
  } catch (error) {
    console.log('error');
  }

  return false;
};
