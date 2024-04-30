import { WritableSignal, signal } from '@angular/core';
import { User } from '../../models/user.model';

export const userStore: WritableSignal<User> = signal({} as User);
