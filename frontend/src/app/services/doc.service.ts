import { HttpClient } from '@angular/common/http';
import { Injectable, WritableSignal, inject, signal } from '@angular/core';
import { Space } from '../models/docs.model';
import { environment } from '../../environments/environment';
import { userStore } from '../shared/stores/user.store';
import { tap } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class DocService {
  private readonly _http: HttpClient = inject(HttpClient);
  private readonly _endpoint: string = `${environment.API_URL}/doc`;
  private readonly _userHeader: string = environment.X_USER_ID;

  public spaces: WritableSignal<Space[]> = signal([]);

  getAllSpacesFromUser(): void {
    console.log('userStore().id', userStore().id);
    this._http
      .get<Space[]>(`${this._endpoint}/space`, {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('access_token')}`,
          'X-User-Id': `${userStore().id}`,
        },
      })
      .pipe(tap((data) => this.spaces.set(data)))
      .subscribe();
  }
}
