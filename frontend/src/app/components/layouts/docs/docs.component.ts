import { Component, EventEmitter, OnInit, Signal, WritableSignal, signal } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { ButtonModule } from 'primeng/button';
import { CardModule } from 'primeng/card';
import { DialogModule } from 'primeng/dialog';
import { DividerModule } from 'primeng/divider';
import { InputTextModule } from 'primeng/inputtext';
import { InputTextareaModule } from 'primeng/inputtextarea';
import { TreeModule, TreeNodeSelectEvent } from 'primeng/tree';
import { HeaderComponent } from '../../../components/header/header.component';
import { Page, Space } from '../../../models/docs.model';
import { DocService } from '../../../services/doc.service';
import { userStore } from '../../../shared/stores/user.store';
import { TreeNode } from 'primeng/api';

interface ISpaceForm {
  name: FormControl<string | null>;
  description: FormControl<string | null>;
  code: FormControl<string | null>;
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
    InputTextareaModule,
    ReactiveFormsModule,
    HeaderComponent,
    RouterModule
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

  constructor(
    private fb: FormBuilder,
    private actRoute: ActivatedRoute,
    private router: Router,
    private service: DocService,
  ) {
    this.pageForm = this.fb.group<ISpaceForm>({
      name: this.fb.control('', Validators.required),
      description: this.fb.control(''),
      code: this.fb.control('', [
        Validators.required,
        Validators.minLength(3),
        Validators.maxLength(3),
      ]),
    });

    this.loading = true;
    this.visible = false;
    this.user = userStore;
    this.pages = this.service.pages;
    this.space = this.service.space;
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

  handleLoginSubmit() { }

  // handleClickOption(event: ListboxClickEvent) {
  //   console.log('test', event.option);
  // }

  onNodeExpand(event: any) {
    console.log(event)
  }

  nodeSelect(event: TreeNodeSelectEvent) {
    console.log('node selected', event)
    this.router.navigate([`/docs/spaces/${this.space().id}/page/${event.node.data}`])
  }
}
