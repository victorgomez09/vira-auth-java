import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { DividerModule } from 'primeng/divider';
import { TreeNode } from 'primeng/api';
import { TreeModule } from 'primeng/tree';
import { ButtonModule } from 'primeng/button'
import { DialogModule } from 'primeng/dialog'
import { InputTextModule } from 'primeng/inputtext'
import { InputTextareaModule } from 'primeng/inputtextarea';

interface ISpaceForm {
  name: FormControl<string | null>;
  description: FormControl<string | null>;
  code: FormControl<string | null>;
}

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [TreeModule, DividerModule, ButtonModule, DialogModule,
    InputTextModule, InputTextareaModule, ReactiveFormsModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css',
})
export class HomeComponent implements OnInit {
  public loading: boolean = false;
  public nodes!: TreeNode[];
  public visible: boolean = false;
  public spaceForm: FormGroup;

  constructor(private cd: ChangeDetectorRef, private fb: FormBuilder) {
    this.spaceForm = this.fb.group<ISpaceForm>({
      name: this.fb.control('', Validators.required),
      description: this.fb.control(''),
      code: this.fb.control('', [
        Validators.required,
        Validators.minLength(3),
        Validators.maxLength(3)
      ]),
    });
  }

  ngOnInit() {
    this.loading = true;
    setTimeout(() => {
      this.nodes = this.initiateNodes();
      this.loading = false;
      this.cd.markForCheck();
    }, 2000);
  }

  initiateNodes(): TreeNode[] {
    return [
      {
        key: '0',
        label: 'Node 0',
        leaf: false,
      },
      {
        key: '1',
        label: 'Node 1',
        leaf: false,
      },
      {
        key: '2',
        label: 'Node 2',
        leaf: false,
      },
    ];
  }

  nodeExpand(event: any) {
    if (!event.node.children) {
      this.loading = true;
      setTimeout(() => {
        event.node.children = [];
        for (let i = 0; i < 3; i++) {
          event.node.children.push({
            key: event.node.key + '-' + i,
            label: 'Node ' + event.node.key + '-' + i,
            leaf: false,
          });
        }
        this.loading = false;
        this.cd.markForCheck();
      }, 500);
    }
  }

  showDialog() {
    this.visible = true;
  }

  handleLoginSubmit() {

  }
}
