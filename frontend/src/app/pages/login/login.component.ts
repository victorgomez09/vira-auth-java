import { Component } from '@angular/core';
import { InputTextModule } from 'primeng/inputtext';
import { CardModule } from 'primeng/card';
import { ButtonModule } from 'primeng/button';
import { PasswordModule } from 'primeng/password';
import {
  AbstractControl,
  FormBuilder,
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';

interface ILoginForm {
  username: FormControl<string | null>;
  password: FormControl<string | null>;
}

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    InputTextModule,
    CardModule,
    ButtonModule,
    PasswordModule,
    ReactiveFormsModule,
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent {
  public loginForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private authService: AuthService,
  ) {
    this.loginForm = this.fb.group<ILoginForm>({
      username: this.fb.control('', Validators.required),
      password: this.fb.control('', [
        Validators.required,
        Validators.minLength(3),
      ]),
    });
  }

  handleLoginSubmit() {
    this.authService.login(this.loginForm.value).subscribe((data) => {
      localStorage.setItem('access_token', data.access_token);
      this.router.navigate(['/docs']);
    });
  }

  get f(): { [key: string]: AbstractControl } {
    return this.loginForm.controls;
  }
}
