import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { EditorModule } from 'primeng/editor';
import { ButtonModule } from 'primeng/button';

@Component({
  selector: 'app-page',
  standalone: true,
  imports: [FormsModule, EditorModule, ButtonModule],
  templateUrl: './page.component.html',
  styleUrl: './page.component.css'
})
export class PageComponent {
  public isEditting: boolean;
  public pageContent: string | undefined;

  constructor() {
    this.isEditting = false;
  }

  handlePageSubmit() {
    console.log('pageContent', this.pageContent)
  }
}
