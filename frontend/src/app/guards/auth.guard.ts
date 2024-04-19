import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';

export const authGuard: CanActivateFn = (_route, _state) => {
  const router = inject(Router);
  const token = localStorage.getItem("access_token");
  if (!token) {
    return router.navigate(["/login"])
  }

  return true;
};
