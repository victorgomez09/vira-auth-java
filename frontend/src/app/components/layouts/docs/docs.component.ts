import { Component, OnInit, Signal, WritableSignal } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { MenuItem, TreeNode } from 'primeng/api';
import { ButtonModule } from 'primeng/button';
import { CardModule } from 'primeng/card';
import { ContextMenuModule } from 'primeng/contextmenu';
import { DialogModule } from 'primeng/dialog';
import { DividerModule } from 'primeng/divider';
import { InputTextModule } from 'primeng/inputtext';
import { InputTextareaModule } from 'primeng/inputtextarea';
import { TreeModule, TreeNodeSelectEvent } from 'primeng/tree';
import { HeaderComponent } from '../../../components/header/header.component';
import { CreatePage, Page, Space } from '../../../models/docs.model';
import { DocService } from '../../../services/doc.service';
import { userStore } from '../../../shared/stores/user.store';
import { CommonModule } from '@angular/common';

interface IPageForm {
  name: FormControl<string | null>;
}

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    TreeModule,
    DividerModule,
    ButtonModule,
    DialogModule,
    InputTextModule,
    CardModule,
    ContextMenuModule,
    InputTextareaModule,
    ReactiveFormsModule,
    HeaderComponent,
    RouterModule,
    CommonModule
  ],
  templateUrl: './docs.component.html',
  styleUrl: './docs.component.css',
})
export class DocsComponent implements OnInit {
  public loading: boolean;
  public visible: boolean;
  public pageForm: FormGroup;
  public user;
  public pages: WritableSignal<TreeNode[]>;
  public space: Signal<Space>;
  public items: MenuItem[];
  public selectedPage: TreeNode | null;

  constructor(
    private fb: FormBuilder,
    private actRoute: ActivatedRoute,
    private router: Router,
    private service: DocService,
  ) {
    this.pageForm = this.fb.group<IPageForm>({
      name: this.fb.control('', Validators.required),
    });

    this.loading = true;
    this.visible = false;
    this.user = userStore;
    this.pages = this.service.pages;
    this.space = this.service.space;

    this.items = [
      { label: 'View', icon: 'pi pi-search', command: () => this.router.navigate([`/docs/spaces/${this.space().id}/page/${this.selectedPage!.data}`]) },
      { label: 'Add', icon: 'pi pi-times', command: () => this.visible = true }
    ];
    this.selectedPage = null;
  }

  ngOnInit(): void {
    this.actRoute.firstChild?.params.subscribe(
      (params: any) => {
        if (params.hasOwnProperty('spaceId') != '') {
          this.service.getSpaceById(params['spaceId']);
          this.service.getPagesBySpace(params['spaceId']);

          this.loading = false;

        }
      });
  }

  showDialog() {
    this.visible = true;
  }

  nodeSelect(event: TreeNodeSelectEvent) {
    this.router.navigate([`/docs/spaces/${this.space().id}/page/${event.node.data}`])
  }

  addNewPage() {
    console.log('values', this.pageForm.value)
    const page: CreatePage = {
      name: this.pageForm.value,
      parent: this.selectedPage?.data
    }

    this.service.createPage(page);
  }

  get f() {
    return this.pageForm.controls
  }
}
