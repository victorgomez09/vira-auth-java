import { Component } from '@angular/core';
import {
  AbstractControl,
  FormBuilder,
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { ButtonModule } from 'primeng/button';
import { CardModule } from 'primeng/card';
import { InputTextModule } from 'primeng/inputtext';
import { PasswordModule } from 'primeng/password';
import { InputNumberModule } from 'primeng/inputnumber';
import { AuthService } from '../../services/auth.service';

interface IRegisterForm {
  firstName: FormControl<string | null>;
  lastName: FormControl<string | null>;
  phone: FormControl<string | null>;
  email: FormControl<string | null>;
  username: FormControl<string | null>;
  password: FormControl<string | null>;
}

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [
    InputTextModule,
    CardModule,
    ButtonModule,
    PasswordModule,
    InputNumberModule,
    ReactiveFormsModule,
    RouterModule,
  ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css',
})
export class RegisterComponent {
  public registerForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private authService: AuthService,
  ) {
    this.registerForm = this.fb.group<IRegisterForm>({
      firstName: this.fb.control('', Validators.required),
      lastName: this.fb.control('', Validators.required),
      phone: this.fb.control('', Validators.required),
      email: this.fb.control('', [Validators.required, Validators.email]),
      username: this.fb.control('', Validators.required),
      password: this.fb.control('', [
        Validators.required,
        Validators.minLength(3),
      ]),
    });
  }

  handleLoginSubmit() {
    this.authService.register(this.registerForm.value).subscribe((data) => {
      this.router.navigate(['/login']);
    });
  }

  get f(): { [key: string]: AbstractControl } {
    return this.registerForm.controls;
  }
}
