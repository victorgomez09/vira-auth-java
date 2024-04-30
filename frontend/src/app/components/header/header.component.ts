import { Component, OnInit } from '@angular/core';
import { MenuItem } from 'primeng/api';
import { MenubarModule } from 'primeng/menubar';
import { AvatarModule } from 'primeng/avatar';
import { userStore } from '../../shared/stores/user.store';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [MenubarModule, AvatarModule],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css',
})
export class HeaderComponent implements OnInit {
  public items: MenuItem[] | undefined;
  public user = userStore;

  ngOnInit() {
    this.items = [
      {
        label: 'Docs',
        icon: 'pi pi-fw pi-file',
        items: [
          {
            label: 'View',
            icon: 'pi pi-fw pi-trash',
          },
          {
            separator: true,
          },
          {
            label: 'New',
            icon: 'pi pi-fw pi-external-link',
          },
        ],
      },
    ];
  }
}
