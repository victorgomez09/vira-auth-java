import { User } from './user.model';

export type Space = {
  id: number;
  name: string;
  code: string;
  description: string;
  users: User[];
};
