import { Routes } from '@angular/router';
import { authGuard } from './guards/auth.guard';
import { DocsComponent } from './components/layouts/docs/docs.component';

export const routes: Routes = [
  {
    path: 'login',
    loadComponent: () =>
      import('./pages/login/login.component').then((m) => m.LoginComponent),
  },
  {
    path: 'register',
    loadComponent: () =>
      import('./pages/register/register.component').then(
        (m) => m.RegisterComponent,
      ),
  },
  {
    path: '',
    loadComponent: () =>
      import('./pages/landing/landing.component').then(
        (m) => m.LandingComponent,
      ),
  },
  {
    path: 'docs',
    component: DocsComponent,
    // canActivate: [authGuard],
    children: [
      {
        path: '',
        loadComponent: () =>
          import('./pages/docs/home/home.component').then((m) => m.HomeComponent),
      },
      {
        path: 'spaces/:id',
        loadComponent: () => import('./pages/docs/space/space.component').then(m => m.SpaceComponent)
      },
      {
        path: 'spaces/:spaceId/page/:pageId',
        loadComponent: () => import('./pages/docs/page/page.component').then(m => m.PageComponent)
      }
    ]
  },
];
