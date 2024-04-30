import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Login, LoginResponse, Register } from '../models/auth.model';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { User } from '../models/user.model';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private readonly _http: HttpClient = inject(HttpClient);
  private readonly endpoint: string = environment.API_URL;

  login(data: Login): Observable<LoginResponse> {
    return this._http.post<LoginResponse>(`${this.endpoint}/auth/login`, data);
  }

  register(data: Register): Observable<User> {
    return this._http.post<User>(`${this.endpoint}/auth/register`, data);
  }
}
