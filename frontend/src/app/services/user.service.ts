import { HttpClient } from '@angular/common/http';
import { Injectable, Signal, inject, signal } from '@angular/core';
import { User } from '../models/user.model';
import { environment } from '../../environments/environment';
import { tap } from 'rxjs';
import { userStore } from '../shared/stores/user.store';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private readonly _http: HttpClient = inject(HttpClient);
  private readonly _endpoint: string = environment.API_URL;

  public getCurrentUser(): void {
    this._http
      .get<User>(`${this._endpoint}/user`, {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('access_token')}`,
        },
      })
      .pipe(
        tap((data) => {
          console.log('data', data);
          userStore.set(data);
        }),
      )
      .subscribe();
  }
}
