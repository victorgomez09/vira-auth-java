import { HttpClient } from '@angular/common/http';
import { Injectable, WritableSignal, inject, signal } from '@angular/core';
import { Page, Space } from '../models/docs.model';
import { environment } from '../../environments/environment';
import { userStore } from '../shared/stores/user.store';
import { tap } from 'rxjs';
import { TreeNode } from 'primeng/api';
import { parsePageToTreeNode } from '../utils/docs.util';

@Injectable({
  providedIn: 'root',
})
export class DocService {
  private readonly _http: HttpClient = inject(HttpClient);
  private readonly _endpoint: string = `${environment.API_URL}/doc`;
  private readonly _userHeader: string = environment.X_USER_ID;

  public spaces: WritableSignal<Space[]> = signal([]);
  public space: WritableSignal<Space> = signal({} as Space);
  public pages: WritableSignal<TreeNode[]> = signal([]);
  public page: WritableSignal<Page> = signal({} as Page);

  /** SPACES **/
  getAllSpacesFromUser(): void {
    this._http
      .get<Space[]>(`${this._endpoint}/space`, {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('access_token')}`,
          // 'X-User-Id': `${userStore().id}`,
          'X-User-Id': `1`,
        },
      })
      .pipe(tap((data) => this.spaces.set(data)))
      .subscribe();
  }

  getSpaceById(spaceId: string): void {
    this._http
      .get<Space>(`${this._endpoint}/space/${spaceId}`, {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('access_token')}`,
          // 'X-User-Id': `${userStore().id}`,
          'X-User-Id': `1`,
        },
      })
      .pipe(tap((data) => this.space.set(data)))
      .subscribe();
  }

  /** PAGES **/
  getPagesBySpace(spaceId: string): void {
    this._http
      .get<Page[]>(`${this._endpoint}/page`, {
        params: { 'spaceId': spaceId },
        headers: {
          Authorization: `Bearer ${localStorage.getItem('access_token')}`,
          // 'X-User-Id': `${userStore().id}`,
          'X-User-Id': `1`,
        },
      })
      .pipe(tap((data) => {
        data.forEach(page => {
          const treePage: TreeNode = {
            key: String(page.treePos),
            label: page.name,
            data: page.id,
            leaf: false
          }
          if (page.subPages) {
            page.subPages.forEach(subPage => {
              treePage.children?.push(
                parsePageToTreeNode(subPage)
              )
            })
          }

          this.pages.update(values => {
            return [...values, treePage];
          });
        })
      }))
      .subscribe();
  }

  getPageById(pageId: string): void {
    this._http
      .get<Page>(`${this._endpoint}/page/${pageId}`, {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('access_token')}`,
          // 'X-User-Id': `${userStore().id}`,
          'X-User-Id': `1`,
        },
      })
      .pipe(tap((data) => this.page.set(data)))
      .subscribe();
  }

  updatePage(data: Page): void {
    this._http
      .put<Page>(`${this._endpoint}/page`, data, {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('access_token')}`,
          // 'X-User-Id': `${userStore().id}`,
          'X-User-Id': `1`,
        },
      })
      .pipe(tap((data) => this.page.set(data)))
      .subscribe();
  }
}
