import { User } from './user.model';

export type Space = {
  id: number;
  name: string;
  code: string;
  description: string;
  users: User[];
};

export type Page = {
  id: string,
  name: string,
  body: string,
  parent: Page,
  owner: string,
  treePos: number,
  subPages: Page[],
  creationDate: Date,
  modificationDate: Date,
  space: Space
}