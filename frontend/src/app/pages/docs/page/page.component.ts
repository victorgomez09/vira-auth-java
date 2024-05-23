import { Component, OnInit, ViewChild, WritableSignal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { ButtonModule } from 'primeng/button';
import { EditorModule } from 'primeng/editor';
import { InputTextModule } from 'primeng/inputtext';
import { ContextMenu, ContextMenuModule } from 'primeng/contextmenu';
import { Page } from '../../../models/docs.model';
import { DocService } from '../../../services/doc.service';
import { MenuItem } from 'primeng/api';
import { SanitizeHtmlPipe } from '../../../shared/pipes/html.pipe';

@Component({
  selector: 'app-page',
  standalone: true,
  imports: [FormsModule, EditorModule, ButtonModule, InputTextModule, ContextMenuModule, SanitizeHtmlPipe],
  templateUrl: './page.component.html',
  styleUrl: './page.component.css'
})
export class PageComponent implements OnInit {
  public isEdittingTittle: boolean;
  public isEditting: boolean;
  public pageContent: string;
  public page: WritableSignal<Page>;
  public items: MenuItem[];

  @ViewChild('cm') cm!: ContextMenu;

  constructor(private activatedRoute: ActivatedRoute, private service: DocService) {
    this.isEdittingTittle = false;
    this.isEditting = false;
    this.page = this.service.page;
    this.pageContent = this.service.page().body

    this.items = [
      {
        label: 'Edit',
        icon: 'pi pi-star',
        shortcut: '⌘+D',
        command: () => {
          console.log('editting')
          console.log('data', this.page().body)
          this.isEditting = true;
        }
      },
      {
        separator: true
      },
      {
        label: 'Share',
        icon: 'pi pi-share-alt',
        items: [
          {
            label: 'Whatsapp',
            icon: 'pi pi-whatsapp',
            badge: '2'
          },
          {
            label: 'Instagram',
            icon: 'pi pi-instagram',
            badge: '3'
          }
        ]
      }
    ];
  }

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe(params => {
      this.service.getPageById(params.get('pageId')!)
    })
  }

  handlePageSubmit() {
    this.service.updatePage(this.page())
    this.isEditting = false;
    this.isEdittingTittle = false;
  }

  onContextMenu(event: any) {
    this.cm.show(event);
  }
}
