import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Login, LoginResponse } from '../models/auth.model';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  login(data: Login): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(`${environment.API_URL}/auth/login`, data);
  }

}
