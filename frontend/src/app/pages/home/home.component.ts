import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { DividerModule } from 'primeng/divider';
import { TreeNode } from 'primeng/api';
import { TreeModule } from 'primeng/tree';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [TreeModule, DividerModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css',
})
export class HomeComponent implements OnInit {
  loading: boolean = false;

  nodes!: TreeNode[];

  constructor(private cd: ChangeDetectorRef) {}

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
}
